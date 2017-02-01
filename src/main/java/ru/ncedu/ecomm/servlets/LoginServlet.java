package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            HttpSession session = req.getSession();
            User user = getDAOFactory().getUserDAO().getUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                session.setAttribute("userId", user.getId());
                session.setAttribute("userRoleId", user.getRoleId());
                req.setAttribute("answer", "User was found");
                resp.sendRedirect("/home");
            }else{
                req.setAttribute("answer", "Uncorrect user! Check email and password");
                req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
            }
    }
}
