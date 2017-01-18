package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        // handle login parameters
        goToUserList(req, resp);
    }

    private void goToUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User userEmail = getDAOFactory().getUserDAO().getUserByEmail(email);
            if (userEmail != null && userEmail.getPassword().equals(password)) {
                req.setAttribute("answer", "User was found");
                req.getRequestDispatcher("/home").forward(req, resp);
            }else{
                req.setAttribute("answer", "Uncorrect user! Check email and password");
                req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
            }
        }catch (NullPointerException e){

        }
    }
}
