package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.services.ShoppingCartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "ShoppingCartIconServlet", urlPatterns = {"/shoppingCartIcon"})
public class ShoppingCartIconServlet extends HttpServlet {
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
        final int EMPTY_QUANTITY = 0;
        HttpSession authorization = req.getSession();
        Long userId = (Long) authorization.getAttribute("userId");
        if (Objects.isNull(userId)) {
            req.setAttribute("quantityProducts", EMPTY_QUANTITY);
        } else {
            showQuantityIfNeed(userId, req);
        }
    }

    private void showQuantityIfNeed(Long userId, HttpServletRequest req) throws SQLException {
        final int EMPTY_QUANTITY = 0;
        Long quantityProducts = getQuantityProducts(userId);
        if (Objects.isNull(quantityProducts)) {
            req.setAttribute("quantityProducts", EMPTY_QUANTITY);
        } else {
            req.setAttribute("quantityProducts", quantityProducts);
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
