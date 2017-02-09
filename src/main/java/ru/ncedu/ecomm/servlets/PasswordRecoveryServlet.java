package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.servlets.services.passwordRecovery.PasswordRecoveryService;
import ru.ncedu.ecomm.servlets.services.passwordRecovery.SendMailService;

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
        String textHTML = getTextHtml(toEmail);
        if (SendMailService.getInstance().checkEmail(toEmail)) {
            req.setAttribute("answer", SendMailService.getInstance().sendMail(toEmail, textHTML));
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        } else{
            req.setAttribute("answer", "Incorrect email! Please try enter other email");
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        }
    }

    private String getTextHtml(String toEmail) {
        return "<p>Please change your password in here:</p>" +
                "<a href='https://ncedu-ecomm-dev.herokuapp.com/passwordChange?email="
                + toEmail + "&recoveryHash=" + PasswordRecoveryService.getInstance()
                .getRecoveryHash() + "'>Change Password</a>";
    }
}