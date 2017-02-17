package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "PasswordChangeServlet", urlPatterns = {"/passwordChange"})
public class PasswordChangeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPassword = req.getParameter("password");
        String email = req.getParameter("email");
        String recoveryHash = req.getParameter("recoveryHash");
        if(checkEmailAndRecoveryHash(email, recoveryHash) ){
            updatePassword(email, newPassword);
            req.setAttribute("answer", "Your password was change."); //TODO: в JSP
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
        else{
            req.setAttribute("answer", "Error. Try again to recovery your password"); //TODO: в JSP
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
    }

    private boolean checkEmailAndRecoveryHash(String email, String recoveryHash) {
        User userByEmail = getDAOFactory().getUserDAO().getUserByEmail(email);
        return recoveryHash.equals(userByEmail.getRecoveryHash());
    }

    private void updatePassword(String email, String newPassword){
        User userNewPassword = getDAOFactory().getUserDAO().getUserByEmail(email);
        userNewPassword.setPassword(newPassword);
        userNewPassword.setRecoveryHash(null);
        getDAOFactory().getUserDAO().updateUser(userNewPassword);
    }
}
