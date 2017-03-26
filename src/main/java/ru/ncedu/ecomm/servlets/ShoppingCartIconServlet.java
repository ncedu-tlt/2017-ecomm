package ru.ncedu.ecomm.servlets;

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
        int EMPTY_QUANTITY = 0;
        Boolean isUserAuthorized = UserService.getInstance().isUserAuthorized(req);
        if (!isUserAuthorized) {
            req.setAttribute(QUANTITY_PRODUCT, EMPTY_QUANTITY);
        } else {
            long userId = UserService.getInstance().getCurrentUserId(req);
            showQuantityIfNeed(userId, req);
        }
    }

    private void showQuantityIfNeed(Long userId, HttpServletRequest req) throws SQLException {
        int EMPTY_QUANTITY = 0;
        Long quantityProducts = getQuantityProducts(userId);
        if (quantityProducts == null) {
            req.setAttribute(QUANTITY_PRODUCT, EMPTY_QUANTITY);
        } else {
            req.setAttribute(QUANTITY_PRODUCT, quantityProducts);
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
}
