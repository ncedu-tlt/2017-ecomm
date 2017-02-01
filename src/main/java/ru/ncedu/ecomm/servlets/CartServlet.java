package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService.getInstance().redirectToLoginIfNeeded(req, resp);
        req.getRequestDispatcher("/views/pages/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService.getInstance().redirectToLoginIfNeeded(req, resp);
        req.getRequestDispatcher("/views/pages/cart.jsp").forward(req, resp);
    }

}