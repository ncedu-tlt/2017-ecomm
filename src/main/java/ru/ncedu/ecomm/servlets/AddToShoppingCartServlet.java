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
        if (userInSystem) {
            long userId = UserService.getInstance().getCurrentUserId(req);
            long productId = Long.parseLong(req.getParameter("productId"));
            addToCart(userId, productId);
            displayQuantityOnPage(userId, resp);
        } else {
            resp.setStatus(500);
            PrintWriter out = resp.getWriter();
            out.println("error");
        }
    }

    private void addToCart(long userId, long productId) throws IOException {
        try {
            ShoppingCartService.getInstance().addToShoppingCart(userId, productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayQuantityOnPage(long userId, HttpServletResponse resp) {
        try {
            Long quantity = getQuantity(userId);
            if (quantity == null) {
                throw new RuntimeException();
            } else {
                PrintWriter out = resp.getWriter();
                out.println(quantity);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Long getQuantity(long userId) throws SQLException {
        Long salesOrderId = ShoppingCartService.getInstance().getSalesOrderId(userId);
        if (salesOrderId == null) {
            return null;
        } else {
            return getDAOFactory().getOrderItemsDAO().getQuantityBySalesOrderId(salesOrderId);
        }
    }
}