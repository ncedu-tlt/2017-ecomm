package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.servlets.services.passwordRecovery.PasswordRecoveryService;
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
        if (!UserValidationUtils.checkEmail(toEmail)) {
            actionOnErrorCheckEmail(req, resp);
        }
        else{
            actionAfterSendingMail(req, resp, toEmail);
        }
    }

    private void actionOnErrorCheckEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answerError = "Incorrect email! Please try enter other email";
        req.setAttribute(ANSWER, answerError);
        req.getRequestDispatcher(Configuration.getProperty("page.passwordRecovery")).forward(req, resp);
    }

    private void actionAfterSendingMail(HttpServletRequest req, HttpServletResponse resp, String toEmail) throws ServletException, IOException {
        String contextPath = req.getServerName();
        String answerFromMailService = PasswordRecoveryService.getInstance().getAnswer(toEmail, contextPath);
        req.setAttribute(ANSWER, answerFromMailService);
        req.getRequestDispatcher(Configuration.getProperty("page.passwordRecovery")).forward(req, resp);
    }
}