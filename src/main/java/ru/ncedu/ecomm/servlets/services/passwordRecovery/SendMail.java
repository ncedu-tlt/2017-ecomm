package ru.ncedu.ecomm.servlets.services.passwordRecovery;

import ru.ncedu.ecomm.data.models.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class SendMail {
    private final String toEmail;
    private final String fromEmail;
    private final String subjectLetter;
    private final Properties SERVER_PROPERTIES;
    private final String textHTML;

    public SendMail(String toMail, String fromMail, String subject, String textHTML) {
        this.toEmail = toMail;
        this.fromEmail = fromMail;
        this.subjectLetter = subject;
        this.textHTML = textHTML;
        this.SERVER_PROPERTIES = configServerForSend();
    }

    private Properties configServerForSend() {
        Properties serverProperties = System.getProperties();
        serverProperties.put("mail.smtp.auth", "true");
        serverProperties.put("mail.smtp.starttls.enable", "true");
        serverProperties.put("mail.smtp.host", "smtp.gmail.com");
        serverProperties.put("mail.smtp.port", "587");
        serverProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
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
        if (matcher.find() && searchMailInDatabase()) {
            return true;
        } else {
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

        Session session = Session.getDefaultInstance(SERVER_PROPERTIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        return sendMessageWithMimeMessage(message);
    }

    private boolean sendMessageWithMimeMessage(MimeMessage message) {
        try {
            message.setFrom(new InternetAddress(this.fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.toEmail));
            message.setSubject(this.subjectLetter);
            message.setContent(this.textHTML, "text/html; charset=utf-8");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            System.out.println(e);
            return false;
        }
    }

    public static SendMail buildSenderByPasswordRecovery(PasswordRecoveryService recoveryService){
        String toEmail = recoveryService.getToEmail();
        String fromEmail = recoveryService.getFromEmail();
        String textHTML = recoveryService.getTextHTML();
        String subjectRecovery = "Password recovery";

        //for test
        System.out.println(recoveryService.getFromEmail());
        System.out.println(recoveryService.getToEmail());
        System.out.println(recoveryService.getTextHTML());
        System.out.println(recoveryService.getRecoveryHash());

        return new SendMail(
                toEmail,
                fromEmail,
                subjectRecovery,
                textHTML
        );
    }
}