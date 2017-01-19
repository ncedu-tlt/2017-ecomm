package ru.ncedu.ecomm.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ProfileIconServlet", urlPatterns = {"/profileIcon"})
public class ProfileIconServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        profileIcon(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private void profileIcon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("userId") != null) {
            req.getRequestDispatcher("/views/pages/profile.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
        }
    }
}
