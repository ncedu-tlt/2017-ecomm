package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.UserDAOObject;
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

    private static final String EMAIL = "email";
    private static final String RECOVERY_HASH = "recoveryHash";
    private static final String RESULT_SUCCESS = "ChangeSuccess";
    private static final String RESULT_ERROR = "ChangeError";
    private static final String ANSWER = "answer";
    private static final String PASSWORD = "password";
    private static final String HIDDEN_HASH = "hiddenHash";
    private static final String HIDDEN_EMAIL = "hiddenEmail";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req = getRequestWithAllAttributes(req);
        req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
    }

    private HttpServletRequest getRequestWithAllAttributes(HttpServletRequest req) {
        req.setAttribute(EMAIL, req.getParameter(EMAIL));
        req.setAttribute(RECOVERY_HASH, req.getParameter(RECOVERY_HASH));

        return req;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOObject userToRecovery = getUserToRecovery(req);
        if(checkEmailAndRecoveryHash(userToRecovery) ){
            updatePassword(userToRecovery);
            req.setAttribute(ANSWER, RESULT_SUCCESS);
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
        else{
            req.setAttribute(ANSWER, RESULT_ERROR);
            req.getRequestDispatcher("/views/pages/passwordChange.jsp").forward(req, resp);
        }
    }

    private boolean checkEmailAndRecoveryHash(UserDAOObject userToRecovery) {
        String recoveryHash = userToRecovery.getRecoveryHash();
        UserDAOObject userByEmail = getDAOFactory().getUserDAO().getUserByEmail(userToRecovery.getEmail());
        return recoveryHash.equals(userByEmail.getRecoveryHash());
    }

    private void updatePassword(UserDAOObject userToRecovery){
        UserDAOObject userWithNewPassword = getDAOFactory().getUserDAO().getUserByEmail(userToRecovery.getEmail());
        String newPassword = EncryptionUtils.getMd5Digest(userToRecovery.getPassword());
        userWithNewPassword.setPassword(newPassword);
        userWithNewPassword.setRecoveryHash(null);
        getDAOFactory().getUserDAO().updateUser(userWithNewPassword);
    }

    private UserDAOObject getUserToRecovery(HttpServletRequest req) {
        UserDAOObject userByRecovery = new UserDAOObject();

        userByRecovery.setPassword(req.getParameter(PASSWORD));
        userByRecovery.setRecoveryHash(req.getParameter(HIDDEN_HASH));
        userByRecovery.setEmail(req.getParameter(HIDDEN_EMAIL));

        return userByRecovery;
    }
}
