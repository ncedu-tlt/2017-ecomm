package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.servlets.services.passwordRecovery.PasswordRecoveryService;
import ru.ncedu.ecomm.servlets.services.passwordRecovery.SendingMailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "PasswordRecoveryServlet", urlPatterns = {"/recovery"})
public class PasswordRecoveryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendLetterToEmail(req, resp);
    }

    private void sendLetterToEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toEmail = req.getParameter("email");
        if (SendingMailService.getInstance().checkEmail(toEmail)) {
            String answerFromMailService = getAnswerAndUpdateRecoveryHash(toEmail);
            req.setAttribute("answer", answerFromMailService);
            req.getRequestDispatcher(Configuration.getProperty("page.passwordRecovery")).forward(req, resp);
        } else {
            String answerError = "Incorrect email! Please try enter other email";
            req.setAttribute("answer", answerError);
            req.getRequestDispatcher(Configuration.getProperty("page.passwordRecovery")).forward(req, resp);
        }
    }


    //TODO: по хорошему бы вынести это в отдельный файл
    private String getTextHtml(String toEmail, String recoveryHash) {
        return "<p>Please change your password in here:</p>" +
                "<a href='https://ncedu-ecomm-dev.herokuapp.com/passwordChange?email="
                + toEmail + "&recoveryHash=" + recoveryHash + "'>Change Password</a>";
    }

    private String getAnswerAndUpdateRecoveryHash(String toEmail) {
        String recoveryHash = PasswordRecoveryService.getInstance().getRecoveryHashByEmail();
        String textHTML = getTextHtml(toEmail, recoveryHash);
        PasswordRecoveryService.getInstance().addRecoveryHashToUser(toEmail, recoveryHash);
        return SendingMailService.getInstance().sendMail(toEmail, textHTML) ?
                "Letter with instructions was sent in your email. Please check your post."
                : "Email address not registered in the database.";
    }
}