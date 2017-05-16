package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.dao.CategoryDAOObject;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static ru.ncedu.ecomm.utils.RedirectUtil.redirectToPage;

@WebServlet(name = "ProductComparatorIconServlet", urlPatterns = {"/compareIcon"})
public class ProductComparatorIconServlet extends HttpServlet {
    private static final String ACTION = "action";
    private static final String PRODUCT_ID_PARAMETER = "productId";
    private static final String REMOVE_PRODUCT = "remove";
    private static final String PARAMETER_UPDATE_SIZE = "updateCompareInIcon";
    private static final String CLEAR_COMPARE_LIST = "clearList";
    private static final String PRODUCTS_SOURCE = "productSource";
    private static final String PRODUCTS_TO_COMPARE = "compareList";
    private static final String CHARS_FOR_PRODUCT = "charForProduct";
    private static final String PRODUCT_TO_COMPARE_LIST_SIZE = "compareListSize";
    private static final String MAX_SIZE_ERROR = "compareListOverflow";
    private static final String INCORRECT_CATEGORY_ERROR = "incorrectCategory";
    private static final String PRODUCT_ALREADY_EXISTS = "productAlreadyExists";
    private static final String PRODUCT_IS_CORRECT = "correct";
    private static final int MAX_SIZE = 3;
    private static final int FIRST_ELEMENT_IN_ARRAY = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addToCompare(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addToCompare(req, resp);
    }

    private void addToCompare(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        switch (req.getParameter(ACTION)) {
            case PARAMETER_UPDATE_SIZE: {
                updateQuantity(req, resp);
                break;
            }
            case CLEAR_COMPARE_LIST: {
                clearCompareList(req, resp);
                break;
            }
            case REMOVE_PRODUCT: {
                removeProduct(req, resp);
                break;
            }
        }
    }

    private void removeProduct(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        long productId = Long.parseLong(req.getParameter(PRODUCT_ID_PARAMETER));
        List<ProductDetailsModel> sourceList = (List<ProductDetailsModel>) session.getAttribute(PRODUCTS_SOURCE);

        sourceList = removeProductFromSourceList(sourceList, productId);

        addProductToListAndSetInSession(session, sourceList, req, resp);
    }

    private void updateDataInSession(HttpSession session,
                                     List<ProductDetailsModel> sourceList,
                                     List<CompareTabCharGroup> compareChars,
                                     List<ProductViewModel> productForCompare) {

        session.removeAttribute(CHARS_FOR_PRODUCT);
        session.removeAttribute(PRODUCTS_SOURCE);
        session.removeAttribute(PRODUCTS_TO_COMPARE);

        session.setAttribute(PRODUCTS_SOURCE, sourceList);
        session.setAttribute(PRODUCTS_TO_COMPARE, productForCompare);
        session.setAttribute(CHARS_FOR_PRODUCT, compareChars);
        session.setAttribute(PRODUCT_TO_COMPARE_LIST_SIZE, sourceList.size());

    }


    private List<ProductDetailsModel> removeProductFromSourceList(List<ProductDetailsModel> sourceList, long productId) {
        Iterator iterator = sourceList.iterator();
        while (iterator.hasNext()) {
            ProductDetailsModel productDetailsModel = (ProductDetailsModel) iterator.next();

            if (productDetailsModel.getId() == productId) {
                iterator.remove();
            }
        }
        return sourceList;
    }

    private void clearCompareList(HttpServletRequest req, HttpServletResponse resp) {
        List<ProductDetailsModel> sourceList = new ArrayList<>();
        List<CompareTabCharGroup> compareChars = new ArrayList<>();
        List<ProductViewModel> productForCompare = new ArrayList<>();

        HttpSession session = req.getSession();
        updateDataInSession(session, sourceList, compareChars, productForCompare);

        redirectToPage(req, resp, Configuration.getProperty("components.blockForProductComparator"));
    }

    private void updateQuantity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<ProductDetailsModel> sourceList;

        long productId = Long.parseLong(req.getParameter(PRODUCT_ID_PARAMETER));

        if (session.getAttribute(PRODUCTS_SOURCE) == null) {
            sourceList = new ArrayList<>();
        } else {
            sourceList = (List<ProductDetailsModel>) session.getAttribute(PRODUCTS_SOURCE);
        }

        if (sourceList.size() < MAX_SIZE) {
            ProductDetailsModel baseProduct = findAndAddProduct(productId);


            switch (checkProductCategoryRepeat(baseProduct, sourceList)) {
                case INCORRECT_CATEGORY_ERROR: {
                    sendMessageToClient(INCORRECT_CATEGORY_ERROR + ","
                            + getCategoryName(sourceList.get(FIRST_ELEMENT_IN_ARRAY)), resp);
                    break;
                }
                case PRODUCT_ALREADY_EXISTS: {
                    sendMessageToClient(PRODUCT_ALREADY_EXISTS, resp);
                    break;
                }
                case PRODUCT_IS_CORRECT: {
                    sourceList.add(baseProduct);
                    addProductToListAndSetInSession(session, sourceList, req, resp);
                    break;
                }
            }

        } else {
            sendMessageToClient(MAX_SIZE_ERROR, resp);
        }
    }

    private String getCategoryName(ProductDetailsModel baseProduct) {
        CategoryDAOObject category = DAOFactory
                .getDAOFactory()
                .getCategoryDAO()
                .getCategoryById(baseProduct.getCategoryId());
        return category.getName() + "," + category.getCategoryId();
    }

    private void addProductToListAndSetInSession(HttpSession session,
                                                 List<ProductDetailsModel> sourceList,
                                                 HttpServletRequest req,
                                                 HttpServletResponse resp) {

        List<CompareTabCharGroup> compareChars = (List<CompareTabCharGroup>) session.getAttribute(CHARS_FOR_PRODUCT);

        if (compareChars == null) {
            compareChars = new ArrayList<>();
        }

        List<ProductViewModel> productForCompare = ProductConversionService
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

        updateDataInSession(session, sourceList, compareChars, productForCompare);
        redirectToPage(req, resp, Configuration.getProperty("components.blockForProductComparator"));

    }

    private void sendMessageToClient(String message, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println(message);
    }

    private String checkProductCategoryRepeat(ProductDetailsModel productToCompare, List<ProductDetailsModel> productToCompareList) {
        for (ProductDetailsModel productViewModel : productToCompareList) {
            if (productViewModel.getId() == productToCompare.getId()) {
                return PRODUCT_ALREADY_EXISTS;
            } else if (productViewModel.getCategoryId() != productToCompare.getCategoryId()) {
                return INCORRECT_CATEGORY_ERROR;
            }
        }
        return PRODUCT_IS_CORRECT;
    }

    private ProductDetailsModel findAndAddProduct(long productId) {
        return ProductViewService.getInstance().getFullProductById(productId);
    }
}
