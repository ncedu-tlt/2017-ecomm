package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.servlets.models.ProductDetailsModel;
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
    private static final String PRODUCTS_TO_COMPARE = "compareList";
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


        session.removeAttribute(PRODUCTS_TO_COMPARE);
        session.removeAttribute(PRODUCT_TO_COMPARE_LIST_SIZE);

        redirectToPage(req, resp, Configuration.getProperty("components.blockForProductComparator"));
    }

    private void updateQuantity(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        List<ProductDetailsModel> productToCompareList;

        boolean isnNotRepeat;
        long productId = Long.parseLong(req.getParameter(PRODUCT_ID_PARAMETER));

        if (session.getAttribute("compareListSize") == null) {
            productToCompareList = new ArrayList<>();
        } else {

            productToCompareList = (List<ProductDetailsModel>) session.getAttribute("compareList");
            if (productToCompareList.size() > MAX_SIZE) {
                productToCompareList = clearList(productToCompareList);

            }
        }

        if (productToCompareList.size() < MAX_SIZE) {
            ProductDetailsModel productToCompare = findAndAddProduct(productId);
            isnNotRepeat = checkProductCategoryRepeat(productToCompare, productToCompareList);

            if (isnNotRepeat) {
                productToCompareList.add(productToCompare);
            }
        }

        session.removeAttribute(PRODUCTS_TO_COMPARE);
        session.setAttribute(PRODUCTS_TO_COMPARE, productToCompareList);
        session.setAttribute(PRODUCT_TO_COMPARE_LIST_SIZE, productToCompareList.size());

        redirectToPage(req, resp, Configuration.getProperty("components.blockForProductComparator"));

    }
    private boolean checkProductCategoryRepeat(ProductDetailsModel productToCompare, List<ProductDetailsModel> productToCompareList) {
        for (ProductDetailsModel productDetailsModel : productToCompareList) {
            if (productDetailsModel.getId() == productToCompare.getId() || productDetailsModel.getCategoryId() != productToCompare.getCategoryId() ){
                return false;
            }
        }
        return true;
    }

    private ProductDetailsModel findAndAddProduct(long productId) {
        return ProductViewService.getInstance().getFullProductById(productId);
    }


    private List<ProductDetailsModel> clearList(List<ProductDetailsModel> productsToClear) {
        List<ProductDetailsModel> productToRemove = new ArrayList<>();

        for (int count = 0; count < productsToClear.size(); count++) {
            if (count > (MAX_SIZE - 1)) {
                productToRemove.add(productsToClear.get(count));
            }
        }

        productsToClear.removeAll(productToRemove);
        return productsToClear;
    }

}
