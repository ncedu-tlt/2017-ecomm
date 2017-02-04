package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.services.ShoppingCartIconService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddToShoppingCartServlet", urlPatterns = {"/addToShoppingCart"})
public class AddToShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = getUserIdFromSession(req, resp);
        long productId = Long.parseLong(req.getParameter("productId"));
        ShoppingCartIconService cartIconService =
                new ShoppingCartIconService(userId, productId);
        cartIconService.addToShoppingCart();
        resp.sendRedirect("/views/pages/cart.jsp");
    }

    private long getUserIdFromSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession authorization = req.getSession();
        if(authorization.getAttribute("userId") == null){
            req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
        }
        return (long) authorization.getAttribute("userId");
    }
}