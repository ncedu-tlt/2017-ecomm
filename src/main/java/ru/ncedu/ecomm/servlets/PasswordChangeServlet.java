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

    private String email;
    private String recoveryHash;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        email = req.getParameter("email");
        recoveryHash = req.getParameter("recoveryHash");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPassword = req.getParameter("password");
        System.out.println(email);
        System.out.println(recoveryHash);
        if(checkEmailAndRecoveryHash(this.email, this.recoveryHash) ){
            updatePassword(this.email, newPassword);
            req.setAttribute("answer", "Your password was change.");
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
        else{
            req.setAttribute("answer", "Error. Try again to recovery your password");
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
