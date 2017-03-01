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
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showSalesOrderList(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        updateSalesOrderList(req);
        showSalesOrderList(req, resp);
    }

    private void updateSalesOrderList(HttpServletRequest request) throws ServletException, IOException {
        long userId = UserService.getInstance().getCurrentUserId(request);
        try {
            formActionOnShoppingCart(request, userId);
            setQuantityInDataBase(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showSalesOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Boolean userInSystem = UserService.getInstance().isUserAuthorized(request);
            if (!userInSystem) {
                request.getRequestDispatcher(Configuration.getProperty("page.login")).forward(request, response);
            }
            long userId = UserService.getInstance().getCurrentUserId(request);
            List<SalesOrderViewModel> salesOrderList = ShoppingCartService.getInstance()
                    .getSalesOrderModelList(EnumOrderStatus.ENTERING.getStatus(), userId);
            request.setAttribute("salesOrderList", salesOrderList);
            request.getRequestDispatcher(Configuration.getProperty("page.cart")).forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void formActionOnShoppingCart(HttpServletRequest request, long userId) throws SQLException {
        try {
            if (request.getParameter("submitButton") != null) {
                switch (request.getParameter("submitButton")) {
                    case "delete": {
                        ShoppingCartService.getInstance().deletedProductInOrderItemDataBase(
                                Long.parseLong(request.getParameter("productId")),
                                Long.parseLong(request.getParameter("salesOrderId")));
                        break;
                    }
                    case "quantity": {
                        if (Integer.parseInt(request.getParameter("quantityValue")) >= 1) {
                            ShoppingCartService.getInstance().updateQuantity(
                                    Long.parseLong(request.getParameter("salesOrderId")),
                                    Integer.parseInt(request.getParameter("quantityValue")),
                                    Long.parseLong(request.getParameter("productId")));
                        }
                        break;
                    }
                    case "emptyTrash": {
                        ShoppingCartService.getInstance().deletedAllProductsInOrderItemDataBase(userId);
                        break;
                    }
                    case "apply": {
                        setLimitInDataBase(
                                BigDecimal.valueOf(Long.parseLong(request.getParameter("limitInput"))),
                                Long.parseLong(request.getParameter("salesOrderId")));
                        break;
                    }
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("exception", "Unknown format");
        }
    }

    private void setLimitInDataBase(BigDecimal limit, long salesOrderId) {
        SalesOrder salesOrder = DAOFactory.getDAOFactory().getSalesOrderDAO().getSalesOrderById(salesOrderId);
        salesOrder.setLimit(limit);
        if (Long.parseLong(String.valueOf(salesOrder.getLimit())) >= 0) {
            DAOFactory.getDAOFactory().getSalesOrderDAO().updateSalesOrder(salesOrder);
        }
    }

    private void setQuantityInDataBase(HttpServletRequest request) throws SQLException {
        if (request.getParameter("input") != null) {
            int input = Integer.parseInt(String.valueOf(request.getParameter("input")));
            long product = Long.parseLong(request.getParameter("product"));
            long sales = Long.parseLong(request.getParameter("salesOrder"));
            ShoppingCartService.getInstance().updateQuantity(sales, input, product);
        }
    }
}