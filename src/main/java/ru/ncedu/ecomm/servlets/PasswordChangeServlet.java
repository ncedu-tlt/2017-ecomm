package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.utils.EncryptionUtils;

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
        User userByRecovery = getUserByRecovery(req);
        if(checkEmailAndRecoveryHash(userByRecovery) ){
            updatePassword(userByRecovery);
            req.setAttribute("answer", "ChangeSuccess");
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
        else{
            req.setAttribute("answer", "ChangeError");
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
    }

    private boolean checkEmailAndRecoveryHash(User userByRecovery) {
        String recoveryHash = userByRecovery.getRecoveryHash();
        User userByEmail = getDAOFactory().getUserDAO().getUserByEmail(userByRecovery.getEmail());
        return recoveryHash.equals(userByEmail.getRecoveryHash());
    }

    private void updatePassword(User userByRecovery){
        User userNewPassword = getDAOFactory().getUserDAO().getUserByEmail(userByRecovery.getEmail());
        String newPassword = EncryptionUtils.getMd5Digest(userByRecovery.getPassword());
        userNewPassword.setPassword(newPassword);
        userNewPassword.setRecoveryHash(null);
        getDAOFactory().getUserDAO().updateUser(userNewPassword);
    }

    private User getUserByRecovery(HttpServletRequest req) {
        User userByRecovery = new User();

        userByRecovery.setPassword(req.getParameter("password"));
        userByRecovery.setRecoveryHash(req.getParameter("recoveryHash"));
        userByRecovery.setEmail(req.getParameter("email"));

        return userByRecovery;
    }
}
