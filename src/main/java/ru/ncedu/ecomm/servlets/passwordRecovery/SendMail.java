package ru.ncedu.ecomm.servlets.passwordRecovery;

import ru.ncedu.ecomm.data.models.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class SendMail {
    private String toEmail;
    private String fromEmail;
    private String subjectLetter;
    private String messageLetter;
    private final Properties SERVER_PROPERETIES;
    private final String recoveryHash;

    public SendMail(String toMail, String fromMail, String subject, String recoveryHash) {
        this.toEmail = toMail;
        this.fromEmail = fromMail;
        this.subjectLetter = subject;
        this.recoveryHash = recoveryHash;
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
        if (searchMailInDatabase() == true) {
            return sendLetterToUser() ?
                    "Letter with instructions was sent in your email. Please check your post." :
                    "Error. Letter wasn't sent in your email. Please try again";
        } else {
            return "Email address not registrated in the databse.";
        }
    }

    public boolean checkEmail() {
        String regPattern = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        Pattern patternEmailValidation = Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(toEmail);
        if(matcher.find() && searchMailInDatabase()){
            return true;
        }
        else{
            return false;
        }
    }

    User userByEmail;
    private boolean searchMailInDatabase() {
        userByEmail = getDAOFactory().getUserDAO().getUserByEmail(toEmail);
        if (userByEmail != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean sendLetterToUser() {
        String username = "netcracker.ecomm@gmail.com";//тут надо указать свой логин от gmail
        String password = "Sx8jfJnbmd";//тут надо указать свой пароль от gmail
        userByEmail = getDAOFactory().getUserDAO().getUserByEmail(toEmail);

        Session session = Session.getInstance(SERVER_PROPERETIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        try {
            sendMessageWithMimeMessage(message);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    private void sendMessageWithMimeMessage(MimeMessage message) throws MessagingException {
        String textHtml = ("<p>Please change your password in here:</p>" +
                "<a href='http://localhost:8050/passwordChange?email="+toEmail+"&recoveryHash="+recoveryHash+"'>Change Password</a>");

        message.setFrom(new InternetAddress(this.fromEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.toEmail));
        message.setSubject(this.subjectLetter);
        message.setContent(textHtml, "text/html; charset=utf-8");
        Transport.send(message);
    }
}