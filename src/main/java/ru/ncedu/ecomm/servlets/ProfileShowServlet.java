package ru.ncedu.ecomm.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ProfileShowServlet", urlPatterns = {"/showProfile"})
public class ProfileShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession authorization = req.getSession();
        if(authorization.getAttribute("userId") == null){
            req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
        }
        else{
            resp.sendRedirect("/views/pages/profile.jsp");
        }
    }
}
