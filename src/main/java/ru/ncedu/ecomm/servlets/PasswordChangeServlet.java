package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String email = getEmailFromURL(req);
        String recoveryHash = getRecoveryHashFromURL(req);
        String newPassword = req.getParameter("password");
        if(checkEmailAndRecoveryHash(email, recoveryHash) ){
            updatePassword(email, newPassword);
            req.setAttribute("answer", "Your password was change.");
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
        else{
            req.setAttribute("answer", "Error. Try again to recovery your password");
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
    }

    private String getEmailFromURL(HttpServletRequest req){
        String pattern = req.getQueryString();
        String[] parametersFromUrl = pattern.split("&");
        String[] parameters = parametersFromUrl[0].split("=");
        String email = parameters[1];

        return email;
    }

    private String getRecoveryHashFromURL(HttpServletRequest req){
        String pattern = req.getQueryString();
        String[] parametersFromUrl = pattern.split("&");
        String[] parameters = parametersFromUrl[1].split("=");
        String recoveryHash = parameters[1];

        return recoveryHash;
    }

    private boolean checkEmailAndRecoveryHash(String email, String recoveryHash) {
        User userByEmail = getDAOFactory().getUserDAO().getUserByEmail(email);
        if(recoveryHash.equals(userByEmail.getRecoveryHash())){
            return true;
        }
        else{
            return false;
        }
    }

    private void updatePassword(String email, String newPassword){
        User userNewPassword = getDAOFactory().getUserDAO().getUserByEmail(email);
        userNewPassword.setPassword(newPassword);
        userNewPassword.setRecoveryHash(null);
        getDAOFactory().getUserDAO().updateUser(userNewPassword);
    }
}
