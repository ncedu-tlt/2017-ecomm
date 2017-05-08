package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.servlets.models.CompareChar;
import ru.ncedu.ecomm.servlets.models.CompareTabCharGroup;
import ru.ncedu.ecomm.servlets.models.ProductDetailsModel;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;
import ru.ncedu.ecomm.servlets.services.ProductConversionService;
import ru.ncedu.ecomm.servlets.services.ProductViewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.utils.RedirectUtil.redirectToPage;

@WebServlet(name = "ProductComparatorIconServlet", urlPatterns = {"/compareIcon"})
public class ProductComparatorIconServlet extends HttpServlet {
    private static final String ACTION = "action";
    private static final String PRODUCT_ID_PARAMETER = "productId";
    private static final String PARAMETER_UPDATE_SIZE = "updateCompareInIcon";
    private static final String CLEAR_COMPARE_LIST = "clearList";
    private static final String PRODUCTS_SOURCE = "productSource";
    private static final String PRODUCTS_TO_COMPARE = "compareList";
    private static final String CHARS_FOR_PRODUCT = "charForProduct";
    private static final String PRODUCT_TO_COMPARE_LIST_SIZE = "compareListSize";
    private static final int MAX_SIZE = 3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addToCompare(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addToCompare(req, resp);
    }

    private void addToCompare(HttpServletRequest req, HttpServletResponse resp) {

        switch (req.getParameter(ACTION)) {
            case PARAMETER_UPDATE_SIZE: {
                updateQuantity(req, resp);
                break;
            }
            case CLEAR_COMPARE_LIST: {
                clearCompareList(req, resp);
                break;
            }
        }
    }

    private void clearCompareList(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();


        session.removeAttribute(PRODUCTS_SOURCE);
        session.removeAttribute(PRODUCTS_TO_COMPARE);
        session.removeAttribute(CHARS_FOR_PRODUCT);
        session.removeAttribute(PRODUCT_TO_COMPARE_LIST_SIZE);

        redirectToPage(req, resp, Configuration.getProperty("components.blockForProductComparator"));
    }

    private void updateQuantity(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        List<ProductDetailsModel> sourceList;
        List<ProductViewModel> productForCompare;
        List<CompareTabCharGroup> compareChars;

        boolean isnNotRepeat;
        long productId = Long.parseLong(req.getParameter(PRODUCT_ID_PARAMETER));

        if (session.getAttribute(PRODUCTS_SOURCE) == null) {
            sourceList = new ArrayList<>();
            productForCompare = new ArrayList<>();
            compareChars = new ArrayList<>();
        } else {
            compareChars = (List<CompareTabCharGroup>) session.getAttribute(CHARS_FOR_PRODUCT);
            productForCompare = (List<ProductViewModel>) session.getAttribute(PRODUCTS_TO_COMPARE);
            sourceList = (List<ProductDetailsModel>) session.getAttribute(PRODUCTS_SOURCE);
        }

        if (sourceList.size() < MAX_SIZE) {
            ProductDetailsModel baseProduct = findAndAddProduct(productId);

            isnNotRepeat = checkProductCategoryRepeat(baseProduct, sourceList);

            if (isnNotRepeat) {
                sourceList.add(baseProduct);

            }
            productForCompare = ProductConversionService
                    .getInstance()
                    .convertSourceListToProductViewModelList(sourceList);

            if (compareChars.size() == 0) {
                compareChars = ProductConversionService
                        .getInstance()
                        .convertSourceListToCharCompareList(sourceList);

            } else {
                compareChars = ProductConversionService
                        .getInstance()
                        .addCharacteristicValueInList(compareChars, sourceList);
            }
        }

        session.removeAttribute(PRODUCTS_SOURCE);
        session.removeAttribute(PRODUCTS_TO_COMPARE);
        session.removeAttribute(CHARS_FOR_PRODUCT);
        session.setAttribute(PRODUCTS_SOURCE, sourceList);
        session.setAttribute(PRODUCTS_TO_COMPARE, productForCompare);
        session.setAttribute(CHARS_FOR_PRODUCT, compareChars);
        session.setAttribute(PRODUCT_TO_COMPARE_LIST_SIZE, sourceList.size());

        redirectToPage(req, resp, Configuration.getProperty("components.blockForProductComparator"));

    }

    private boolean checkProductCategoryRepeat(ProductDetailsModel productToCompare, List<ProductDetailsModel> productToCompareList) {
        for (ProductDetailsModel productViewModel : productToCompareList) {
            if (productViewModel.getId() == productToCompare.getId() || productViewModel.getCategoryId() != productToCompare.getCategoryId()) {
                return false;
            }
        }
        return true;
    }

    private ProductDetailsModel findAndAddProduct(long productId) {
        return ProductViewService.getInstance().getFullProductById(productId);
    }
}
