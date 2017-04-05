package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.servlets.services.ShoppingCartService;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "ShoppingCartIconServlet", urlPatterns = {"/shoppingCartIcon"})
public class ShoppingCartIconServlet extends HttpServlet {
    private static final String QUANTITY_PRODUCT = "quantityProducts";
    private static final String ADD_TO_CART_URL = "addToCartURL";
    private static final String LOGIN_URL = "loginURL";
    private static final String ADD_TO_CART_SERVLET = Configuration.getProperty("servlet.addToShoppingCart");
    private static final String LOGIN_SERVLET = Configuration.getProperty("servlet.login");
    private static final int EMPTY_QUANTITY = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            showQuantity(req);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.doGet(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showQuantity(HttpServletRequest req) throws ServletException, IOException, SQLException {
        Boolean isUserAuthorized = UserService.getInstance().isUserAuthorized(req);
        if (isUserAuthorized) {
            long userId = UserService.getInstance().getCurrentUserId(req);
            showQuantityIfNeed(userId, req);
        }
        else {
            setAttributesToRequest(req, EMPTY_QUANTITY);
        }
    }

    private void showQuantityIfNeed(Long userId, HttpServletRequest req) throws SQLException {
        Long quantityProducts = getQuantityProducts(userId);
        if (quantityProducts == null) {
            setAttributesToRequest(req, EMPTY_QUANTITY);
        } else {
            setAttributesToRequest(req, quantityProducts);
        }
    }

    private Long getQuantityProducts(long userId) throws SQLException {
        Long salesOrderId = ShoppingCartService.getInstance().getSalesOrderId(userId);
        if (salesOrderId == null) {
            return null;
        } else {
            return getDAOFactory().getOrderItemsDAO().
                    getQuantityBySalesOrderId(salesOrderId);
        }
    }

    private void setAttributesToRequest(HttpServletRequest req, long quantity) {
        String addToCartURL = req.getContextPath() + ADD_TO_CART_SERVLET;
        String loginURL = req.getContextPath() + LOGIN_SERVLET;
        req.setAttribute(ADD_TO_CART_URL, addToCartURL);
        req.setAttribute(LOGIN_URL, loginURL);

        req.setAttribute(QUANTITY_PRODUCT, quantity);
    }
}
