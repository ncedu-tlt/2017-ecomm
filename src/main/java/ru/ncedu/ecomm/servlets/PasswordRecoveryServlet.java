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
        sendLetterToEmail(req, resp);
    }

    private void sendLetterToEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toEmail = req.getParameter(EMAIL);
        if (!UserValidationUtils.checkEmail(toEmail)) {
            actionOnErrorCheckEmail(req, resp);
        }
        else{
            actionAfterSendingMail(req, resp, toEmail);
        }
    }

    private void actionOnErrorCheckEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ANSWER, ERROR_INPUT_EMAIL);
        req.getRequestDispatcher(RECOVERY_REDIRECT).forward(req, resp);
    }

    private void actionAfterSendingMail(HttpServletRequest req, HttpServletResponse resp, String toEmail) throws ServletException, IOException {
        UserDAOObject userByEmail = getDAOFactory().getUserDAO().getUserByEmail(toEmail);
        if(userByEmail == null)
            actionOnErrorInputEmail(req, resp);
        else {
            actionForSendingLetterToEmail(req, resp, userByEmail);
        }
    }

    private void actionOnErrorInputEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ANSWER, ERROR_EMAIL_FOUND);
        req.getRequestDispatcher(RECOVERY_REDIRECT).forward(req, resp);
    }

    private void actionForSendingLetterToEmail(HttpServletRequest req, HttpServletResponse resp, UserDAOObject userByEmail) throws ServletException, IOException {
        String contextPath = req.getServerName();
        String answerFromMailService = PasswordRecoveryService.getInstance().getAnswer(userByEmail.getEmail(), contextPath);
        req.setAttribute(ANSWER, answerFromMailService);
        req.getRequestDispatcher(RECOVERY_REDIRECT).forward(req, resp);
    }
}