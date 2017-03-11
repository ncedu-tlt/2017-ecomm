package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.services.ShoppingCartService;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "AddToShoppingCartServlet", urlPatterns = {"/addToShoppingCart"})
public class AddToShoppingCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean userInSystem = UserService.getInstance().isUserAuthorized(req);
        if (!userInSystem) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            long userId = UserService.getInstance().getCurrentUserId(req);
            long productId = Long.parseLong(req.getParameter("productId"));
            AddToCart(userId, productId);
            displayQuantityOnPage(userId, resp);
        }
    }

    private void displayQuantityOnPage(long userId, HttpServletResponse resp) {
        try {
            long quantity = getQuantity(userId);
            PrintWriter out = resp.getWriter();
            out.println(quantity);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void AddToCart(long userId, long productId) throws IOException {
        try {
            ShoppingCartService.getInstance().addToShoppingCart(userId, productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long getQuantity(long userId) throws SQLException {
        Long salesOrderId = ShoppingCartService.getInstance().getSalesOrderId(userId);
        return getDAOFactory().getOrderItemsDAO().getProductsBySalesOrderId(salesOrderId);
    }
}