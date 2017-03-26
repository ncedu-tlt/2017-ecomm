package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.servlets.services.passwordRecovery.PasswordRecoveryService;
import ru.ncedu.ecomm.servlets.services.passwordRecovery.SendingMailService;
import ru.ncedu.ecomm.utils.UserValidationUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "PasswordRecoveryServlet", urlPatterns = {"/recovery"})
public class PasswordRecoveryServlet extends HttpServlet {

    private static final String EMAIL = "email";
    private static final String ANSWER = "answer";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendLetterToEmail(req, resp);
    }

    private void sendLetterToEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toEmail = req.getParameter(EMAIL);
        if (UserValidationUtils.checkEmail(toEmail)) {
            String answerFromMailService = getAnswerAndUpdateRecoveryHash(toEmail,req);
            req.setAttribute(ANSWER, answerFromMailService);
            req.getRequestDispatcher(Configuration.getProperty("page.passwordRecovery")).forward(req, resp);
        } else {
            String answerError = "Incorrect email! Please try enter other email";
            req.setAttribute(ANSWER, answerError);
            req.getRequestDispatcher(Configuration.getProperty("page.passwordRecovery")).forward(req, resp);
        }
    }


    private String getAnswerAndUpdateRecoveryHash(String toEmail, HttpServletRequest req) {
        String recoveryHash = PasswordRecoveryService.getInstance().getRecoveryHashByEmail();
        String textHTML = getTextHtml(toEmail, recoveryHash, req);
        PasswordRecoveryService.getInstance().addRecoveryHashToUser(toEmail, recoveryHash);
        return SendingMailService.getInstance().sendMail(toEmail, textHTML) ?
                "Letter with instructions was sent in your email. Please check your post."
                : "Email address not registered in the database.";
    }

    private String getTextHtml(String toEmail, String recoveryHash, HttpServletRequest req) {
        String contextPath = req.getServerName();
        return "<p>Please change your password in here:</p>" +
                "<a href='https://"+contextPath+"/passwordChange?email="
                + toEmail + "&recoveryHash=" + recoveryHash + "'>Change Password</a>";
    }
}