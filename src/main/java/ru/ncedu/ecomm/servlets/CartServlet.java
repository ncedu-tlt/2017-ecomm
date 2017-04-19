package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.data.models.SalesOrderDAOObject;
import ru.ncedu.ecomm.servlets.models.EnumOrderStatus;
import ru.ncedu.ecomm.servlets.services.ShoppingCartService;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import static ru.ncedu.ecomm.utils.RedirectUtil.redirectToPage;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    private static final String INPUT_LIMIT = "inputLimit";
    private static final String SALES_ORDER = "salesOrder";
    private static final String ACTION = "action";
    private static final String PRODUCT_ID = "productId";
    private static final String SALES_ORDER_ID = "salesOrderId";
    private static final String INPUT = "input";
    private static final String DELETE = "delete";
    private static final String EMPTY_CART = "emptyCart";
    private static final String CHECKOUT = "checkout";
    private static final String SAVE_LIMIT = "saveLimit";
    private static final String SAVE_QUANTITY = "saveQuantity";
    private static final String SHOPPING_CART_URL = "shoppingCartUrl";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean userInSystem = UserService.getInstance().isUserAuthorized(req);
        if (!userInSystem) {
            redirectToPage(req, resp, Configuration.getProperty("servlet.login"));
        } else {
            showSalesOrder(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        updateSalesOrder(req, resp);
    }

    private void updateSalesOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userId = UserService.getInstance().getCurrentUserId(request);
        try {
            processActions(request, response, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showSalesOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String shoppingCartUrl = request.getContextPath() + Configuration.getProperty("servlet.cart");
            long userId = UserService.getInstance().getCurrentUserId(request);
            SalesOrder salesOrder = ShoppingCartService.getInstance()
                    .getSalesOrderModel(EnumOrderStatus.ENTERING.getStatus(), userId);
            request.setAttribute(SHOPPING_CART_URL, shoppingCartUrl);
            request.setAttribute(SALES_ORDER, salesOrder);
            request.getRequestDispatcher(Configuration.getProperty("page.cart")).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void processActions(HttpServletRequest request, HttpServletResponse response, long userId) throws SQLException {
        try {
            if (request.getParameter(ACTION) != null) {
                switch (request.getParameter(ACTION)) {
                    case DELETE: {
                        ShoppingCartService.getInstance().deletedProductsInOrderItem(
                                Long.parseLong(request.getParameter(PRODUCT_ID)),
                                Long.parseLong(request.getParameter(SALES_ORDER_ID)));
                        showSalesOrder(request, response);
                        break;
                    }
                    case EMPTY_CART: {
                        ShoppingCartService.getInstance().deletedAllProductsInOrderItemDataBase(userId);
                        showSalesOrder(request, response);
                        break;
                    }
                    case CHECKOUT: {
                        setOrderStatusId(userId);
                        redirectToPage(request, response, Configuration.getProperty("page.submitOrder"));
                        break;
                    }
                    case SAVE_LIMIT: {
                        setLimitInDataBase(request);
                        break;
                    }
                    case SAVE_QUANTITY: {
                        setQuantityInDataBase(request);
                        break;
                    }
                }
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void setOrderStatusId(long userId) throws SQLException {
        long salesOrderId = ShoppingCartService.getInstance().getSalesOrderId(userId);
        SalesOrderDAOObject salesOrder = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderById(salesOrderId);
        salesOrder.setOrderStatusId(EnumOrderStatus.SUBMITTED.getStatus());
        DAOFactory.getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
    }

    private void setLimitInDataBase(HttpServletRequest request) {
        BigDecimal inputLimit = BigDecimal.valueOf(Long.parseLong(request.getParameter(INPUT_LIMIT)));
        long sales = Long.parseLong(request.getParameter(SALES_ORDER_ID));
        SalesOrderDAOObject salesOrder = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderById(sales);
        salesOrder.setLimit(inputLimit);
        int compare = salesOrder.getLimit().compareTo(BigDecimal.ZERO);
        if (compare > 0) {
            DAOFactory.getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
        } else if (compare == 0) {
            salesOrder.setLimit(null);
            DAOFactory.getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
        }
    }

    private void setQuantityInDataBase(HttpServletRequest request) throws SQLException {
        if (request.getParameter(INPUT) != null) {
            int input = Integer.parseInt(request.getParameter(INPUT));
            long product = Long.parseLong(request.getParameter(PRODUCT_ID));
            long sales = Long.parseLong(request.getParameter(SALES_ORDER_ID));
            ShoppingCartService.getInstance().updateQuantity(sales, input, product);
        }
    }
}