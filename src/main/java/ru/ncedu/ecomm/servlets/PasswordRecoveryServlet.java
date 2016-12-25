package ru.ncedu.ecomm.servlets;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "PasswordRecoveryServlet", urlPatterns = {"/recovery"})
public class PasswordRecoveryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // handle operations
        sendLettersEmail(req, resp);
    }

    private void sendLettersEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toEmail = req.getParameter("email");
        String fromEmail = "overylord@mail.ru";
        SendMail sender = new SendMail(toEmail, fromEmail, "Test message", "Hello. It's a first letter: yandex.ru");

        if (sender.sendMail()) {
            req.setAttribute("answer", true);
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        } else {
            req.setAttribute("answer", false);
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        }
    }
}

class SendMail {
    private String toEmail;
    private String fromEmail;
    private String subjectLetter;
    private String messageLetter;
    private final Properties SERVER_PROPERETIES;

    SendMail(String toMail, String fromMail, String subject, String message) {
        this.toEmail = toMail;
        this.fromEmail = fromMail;
        this.subjectLetter = subject;
        this.messageLetter = message;
        SERVER_PROPERETIES = configServerForSend();
    }

    public Properties configServerForSend() {
        Properties serverProperties = new Properties();
        serverProperties.put("mail.smtp.auth", "true");
        serverProperties.put("mail.smtp.starttls.enable", "true");
        serverProperties.put("mail.smtp.host", "smtp.gmail.com");
        serverProperties.put("mail.smtp.port", "587");

        return serverProperties;
    }

    public boolean sendMail() {
        if (checkEmail() == true) {
            return sendLetterToUser();
        } else {
            return false;
        }
    }

    public boolean checkEmail() {
        Pattern patternEmailValidation = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(toEmail);
        return matcher.find();
    }

    public boolean sendLetterToUser() {
        String username = "";//тут надо указать свой логин от gmail
        String password = "";//тут надо указать свой пароль от gmail
        Session session = Session.getInstance(SERVER_PROPERETIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(this.fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.toEmail));
            message.setSubject(this.subjectLetter);
            message.setText(this.messageLetter);


            Transport.send(message);
        } catch (MessagingException e) {
            return false;
        }

        return true;
    }
}
