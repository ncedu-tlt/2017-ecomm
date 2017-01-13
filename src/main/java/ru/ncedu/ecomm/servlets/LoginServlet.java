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

    private void goToUserList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (checkEmailAndPassword(email, password)) {
            req.setAttribute("answer", "User was found");
            req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
        }else{
            req.setAttribute("answer", "Uncorrect email! Please try enter other email");
            req.getRequestDispatcher("/views/pages/login.jsp").forward(req, resp);
        }
    }

    private boolean checkEmailAndPassword(String email, String password) {
        User userEmail = getDAOFactory().getUserDAO().getUserByEmail(email);
        if(password.equals(userEmail.getPassword())){
            return true;
        }
        else{
            return false;
        }
    }
}
