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
        req = getRequestWithAllAttributes(req);
        req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
    }

    private HttpServletRequest getRequestWithAllAttributes(HttpServletRequest req) {
        String email = req.getParameter("email");
        String recoveryHash = req.getParameter("recoveryHash");
        req.setAttribute("email", email);
        req.setAttribute("recoveryHash", recoveryHash);

        return req;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userToRecovery = getUserToRecovery(req);
        if(checkEmailAndRecoveryHash(userToRecovery) ){
            updatePassword(userToRecovery);
            req.setAttribute("answer", "ChangeSuccess");
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
        else{
            req.setAttribute("answer", "ChangeError");
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
    }

    private boolean checkEmailAndRecoveryHash(User userToRecovery) {
        String recoveryHash = userToRecovery.getRecoveryHash();
        User userByEmail = getDAOFactory().getUserDAO().getUserByEmail(userToRecovery.getEmail());
        return recoveryHash.equals(userByEmail.getRecoveryHash());
    }

    private void updatePassword(User userToRecovery){
        User userWithNewPassword = getDAOFactory().getUserDAO().getUserByEmail(userToRecovery.getEmail());
        String newPassword = EncryptionUtils.getMd5Digest(userToRecovery.getPassword());
        userWithNewPassword.setPassword(newPassword);
        userWithNewPassword.setRecoveryHash(null);
        getDAOFactory().getUserDAO().updateUser(userWithNewPassword);
    }

    private User getUserToRecovery(HttpServletRequest req) {
        User userByRecovery = new User();

        userByRecovery.setPassword(req.getParameter("password"));
        userByRecovery.setRecoveryHash(req.getParameter("hiddenHash"));
        userByRecovery.setEmail(req.getParameter("hiddenEmail"));

        return userByRecovery;
    }
}
