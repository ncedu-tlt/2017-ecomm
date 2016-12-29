package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

/**
 * Created by Alexander on 26.12.2016.
 */
@WebServlet(name = "PasswordChangeServlet", urlPatterns = {"/passwordChange"})
public class PasswordChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] parameters = getParameters(req);

        String newPassword = req.getParameter("password");
        req.setAttribute("answer", parameters);
        req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
    }

    private String[] getParameters(HttpServletRequest req){
        String patternUrl = req.getQueryString();
        String[] parametersFromUrl = patternUrl.split("=");
        String[] parametersFromArray = parametersFromUrl[1].split("&");

        String email = parametersFromArray[0];
        String recoveryHash = parametersFromArray[1];

        return parametersFromArray;
    }

    private String changePassword(String newPassword, String email) {

        if (checkPassword(newPassword)) {
            List<User> users = getDAOFactory().getUserDAO().getUsers();
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    user.setPassword(newPassword);
                }
            }
            return "Password was changed";
        } else {
            return "Error! Password wasn't changed";
        }
    }

    private boolean checkPassword(String newPassword) {
        if (newPassword.length() < 4) {
            return false;
        } else if (newPassword.length() > 4 && newPassword.length() < 20) {
            return true;
        } else {
            return false;
        }
    }
}
