package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.models.UserDAOObject;
import ru.ncedu.ecomm.servlets.services.passwordRecovery.PasswordRecoveryService;
import ru.ncedu.ecomm.utils.UserValidationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;


@WebServlet(name = "PasswordRecoveryServlet", urlPatterns = {"/recovery"})
public class PasswordRecoveryServlet extends HttpServlet {

    private static final String EMAIL = "email";
    private static final String ANSWER = "answer";
    private static final String ERROR_INPUT_EMAIL = "ErrorInputEmail";
    private static final String ERROR_EMAIL_FOUND = "ErrorEmailNotFound";
    private static final String RECOVERY_REDIRECT = Configuration.getProperty("page.passwordRecovery");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(RECOVERY_REDIRECT).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (UserValidationUtils.checkEmail(req.getParameter(EMAIL)))
            sendLetterToEmail(req, resp);
        else {
            req.setAttribute(ANSWER, ERROR_INPUT_EMAIL);
            req.getRequestDispatcher(RECOVERY_REDIRECT).forward(req, resp);
        }
    }

    private void sendLetterToEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOObject userByEmail = getDAOFactory().getUserDAO().getUserByEmail(req.getParameter(EMAIL));
        if (userByEmail == null) {
            req.setAttribute(ANSWER, ERROR_EMAIL_FOUND);
            req.getRequestDispatcher(RECOVERY_REDIRECT).forward(req, resp);
        } else {
            outputSuccessSendMail(req, resp, userByEmail);
        }
    }

    private void outputSuccessSendMail(HttpServletRequest req, HttpServletResponse resp, UserDAOObject userByEmail) throws ServletException, IOException {
        String resultSendMailToUser = PasswordRecoveryService.getInstance()
                .sendMailToUser(userByEmail.getEmail(), getContextPath(req));
        req.setAttribute(ANSWER, resultSendMailToUser);
        req.getRequestDispatcher(RECOVERY_REDIRECT).forward(req, resp);
    }

    private String getContextPath(HttpServletRequest req){
        return req.getScheme() + "://" + req.getHeader("Host");
    }
}