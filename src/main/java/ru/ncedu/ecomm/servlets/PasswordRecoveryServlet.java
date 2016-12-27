package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

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
//        User userByEmail = getDAOFactory().getUserDAO().getUserById(6);
//        User users = getDAOFactory().getUserDAO().addRecoveryHash(userByEmail, "12wfsfws");

        String toEmail = req.getParameter("email");
        String fromEmail = "overylord@mail.ru";

        SendMail sender = new SendMail(toEmail, fromEmail, "Test message", "Hello. It's a first letter: yandex.ru");

        if (sender.checkEmail()) {
            req.setAttribute("answer", sender.sendMail());
            req.getRequestDispatcher("/views/pages/passwordRecovery.jsp").forward(req, resp);
        } else if (sender.checkEmail()) {
            req.setAttribute("answer", "Uncorrect email! Please try enter other email");
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

    private Properties configServerForSend() {
        Properties serverProperties = new Properties();
        serverProperties.put("mail.smtp.auth", "true");
        serverProperties.put("mail.smtp.starttls.enable", "true");
        serverProperties.put("mail.smtp.host", "smtp.gmail.com");
        serverProperties.put("mail.smtp.port", "587");

        return serverProperties;
    }

    public String sendMail() {
        if (searchMailInDatabase(this.toEmail) == true) {
            return sendLetterToUser() ?
                    "Letter with instructions was sent in your email." :
                    "Error. Letter wasn't sent in your email. Please try again";
        } else {
            return "Email address not registrated in the databse.";
        }
    }

    public boolean checkEmail() {
        String regPattern = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        Pattern patternEmailValidation = Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(toEmail);
        return matcher.find();
    }

    private boolean searchMailInDatabase(String email) {
        List<User> users = getDAOFactory().getUserDAO().getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean sendLetterToUser() {
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
            message.setContent("<a href='http://localhost:8050/passwordChange'></a>", "text/html; charset=utf-8");


            Transport.send(message);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }
}
