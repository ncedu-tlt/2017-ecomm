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

    private final String INPUT_LIMIT = "inputLimit";
    private final BigDecimal COMPARE_DECIMAL = new BigDecimal("0.00");
    private final String SALES_ORDER = "salesOrder";
    private final String SUBMIT_BUTTON = "submitButton";
    private final String PRODUCT_ID = "productId";
    private final String SALES_ORDER_ID = "salesOrderId";
    private final String INPUT = "input";
    private final String DELETE = "delete";
    private final String EMPTY_CART = "emptyCart";
    private final String CHECKOUT = "checkout";




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean userInSystem = UserService.getInstance().isUserAuthorized(req);
        if (!userInSystem) {
            redirectToPage(req, resp, Configuration.getProperty("servlet.login"));
        }else {
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
            setQuantityInDataBase(request);
            setLimitInDataBase(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showSalesOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long userId = UserService.getInstance().getCurrentUserId(request);
            SalesOrder salesOrder = ShoppingCartService.getInstance()
                    .getSalesOrderModel(EnumOrderStatus.ENTERING.getStatus(), userId);
            request.setAttribute(SALES_ORDER, salesOrder);
            request.getRequestDispatcher(Configuration.getProperty("page.cart")).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void processActions(HttpServletRequest request, HttpServletResponse response, long userId) throws SQLException {
        try {
            if (request.getParameter(SUBMIT_BUTTON) != null) {
                switch (request.getParameter(SUBMIT_BUTTON)) {
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
                }
            }
        } catch (NumberFormatException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void setOrderStatusId(long userId) throws SQLException {
        long salesOrderId = ShoppingCartService.getInstance().getSalesOrderId(userId);
        SalesOrderDAOObject salesOrder = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderById(salesOrderId);
        salesOrder.setOrderStatusId(EnumOrderStatus.SUBMITTED.getStatus());
        DAOFactory.getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
    }

    private void setLimitInDataBase(HttpServletRequest request){
        BigDecimal inputLimit = BigDecimal.valueOf(Long.parseLong(request.getParameter(INPUT_LIMIT)));
        long sales = Long.parseLong(request.getParameter(SALES_ORDER_ID));
        SalesOrderDAOObject salesOrder = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderById(sales);
        salesOrder.setLimit(inputLimit);
        int compare = salesOrder.getLimit().compareTo(COMPARE_DECIMAL);
        if (compare > 0) {
            DAOFactory.getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
        }
        else if (compare == 0) {
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