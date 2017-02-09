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

@WebServlet(name = "AddToShoppingCartServlet", urlPatterns = {"/addToShoppingCart"})
public class AddToShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = UserService.getInstance().getCurrentUserId(req, resp);
        long productId = Long.parseLong(req.getParameter("productId"));
        try {
            ShoppingCartService.getInstaince().addToShoppingCart(userId, productId);
            resp.sendRedirect("/views/pages/cart.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}