package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.servlets.models.EnumOrderStatus;
import ru.ncedu.ecomm.servlets.models.SalesOrderViewModel;
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

    private final BigDecimal COMPARE_DECIMAL = new BigDecimal("0.00");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean userInSystem = UserService.getInstance().isUserAuthorized(req);
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
            actionsInShoppingCart(request, response, userId);
            setQuantityInDataBase(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showSalesOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long userId = UserService.getInstance().getCurrentUserId(request);
            SalesOrderViewModel salesOrder = ShoppingCartService.getInstance()
                    .getSalesOrderModel(EnumOrderStatus.ENTERING.getStatus(), userId);
            request.setAttribute("salesOrder", salesOrder);
            request.getRequestDispatcher(Configuration.getProperty("page.cart")).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void actionsInShoppingCart(HttpServletRequest request, HttpServletResponse response, long userId) throws SQLException {
        try {
            if (request.getParameter("submitButton") != null) {
                switch (request.getParameter("submitButton")) {
                    case "delete": {
                        ShoppingCartService.getInstance().deletedProductsInOrderItem(
                                Long.parseLong(request.getParameter("productId")),
                                Long.parseLong(request.getParameter("salesOrderId")));
                        showSalesOrder(request, response);
                        break;
                    }
                    case "quantity": {
                        updateQuantity(request);
                        showSalesOrder(request, response);
                        break;
                    }
                    case "emptyCart": {
                        ShoppingCartService.getInstance().deletedAllProductsInOrderItemDataBase(userId);
                        showSalesOrder(request, response);
                        break;
                    }
                    case "apply": {
                        setLimitInDataBase(
                                BigDecimal.valueOf(Long.parseLong(request.getParameter("limitInput"))),
                                Long.parseLong(request.getParameter("salesOrderId")));
                        showSalesOrder(request, response);
                        break;
                    }
                    case "checkout": {
                        setOrderStatusId(userId);
                        redirectToPage(request, response, Configuration.getProperty("page.submitOrder"));
                        break;
                    }
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("exception", "Unknown format");
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void setOrderStatusId(long userId) throws SQLException {
        long salesOrderId = ShoppingCartService.getInstance().getSalesOrderId(userId);
        SalesOrder salesOrder = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderById(salesOrderId);
        salesOrder.setOrderStatusId(EnumOrderStatus.SUBMITTED.getStatus());
        DAOFactory.getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
    }

    private void setLimitInDataBase(BigDecimal limit, long salesOrderId) {
        SalesOrder salesOrder = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderById(salesOrderId);
        salesOrder.setLimit(limit);
        int compare = salesOrder.getLimit().compareTo(COMPARE_DECIMAL);
        if (compare >= 0) {
            DAOFactory.getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
        }
    }

    private void setQuantityInDataBase(HttpServletRequest request) throws SQLException {
        if (request.getParameter("input") != null) {
            int input = Integer.parseInt(request.getParameter("input"));
            long product = Long.parseLong(request.getParameter("product"));
            long sales = Long.parseLong(request.getParameter("salesOrder"));
            ShoppingCartService.getInstance().updateQuantity(sales, input, product);
        }
    }

    private void updateQuantity(HttpServletRequest request) throws SQLException {
        if (Integer.parseInt(request.getParameter("quantityValue")) >= 1) {
            ShoppingCartService.getInstance().updateQuantity(
                    Long.parseLong(request.getParameter("salesOrderId")),
                    Integer.parseInt(request.getParameter("quantityValue")),
                    Long.parseLong(request.getParameter("productId")));
        } else {
            ShoppingCartService.getInstance().deletedProductsInOrderItem(
                    Long.parseLong(request.getParameter("productId")),
                    Long.parseLong(request.getParameter("salesOrderId")));
        }
    }
}