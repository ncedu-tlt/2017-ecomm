package ru.ncedu.ecomm.servlets.services.passwordRecovery;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.models.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class SendingMailService {
    private final Properties SERVER_PROPERTIES = configServerForSend();

    private SendingMailService(){
    }

    private static SendingMailService instance;
    public static synchronized SendingMailService getInstance(){
        if(instance == null){
            instance = new SendingMailService();
        }
        return instance;
    }

    private Properties configServerForSend() {
        Properties serverProperties = System.getProperties();
        serverProperties.setProperty("mail.smtp.auth", "true");
        serverProperties.setProperty("mail.smtp.starttls.enable", "true");
        serverProperties.setProperty("mail.smtp.host", "smtp.gmail.com");
        serverProperties.setProperty("mail.smtp.port", "587");
        serverProperties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        return serverProperties;
    }

    public boolean sendMail(String toEmail, String textHtml) {
        return checkEmail(toEmail) && searchMailInDatabase(toEmail) && sendLetterToUser(toEmail, textHtml);
    }

    public boolean checkEmail(String toEmail) {
        String regPattern = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        Pattern patternEmailValidation = Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(toEmail);
        return matcher.find();
    }


    private boolean searchMailInDatabase(String toEmail) {
        User userByEmail = getDAOFactory().getUserDAO().getUserByEmail(toEmail);
        return userByEmail != null;
    }

    private boolean sendLetterToUser(String toEmail, String textHtml) {
        return sendMessageWithMimeMessage(toEmail, textHtml);
    }

    private boolean sendMessageWithMimeMessage( String toEmail, String textHtml) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        return setMimeMessage(message, toEmail, textHtml) && sendToTransport(message);
    }

    private boolean setMimeMessage(MimeMessage message, String toEmail, String textHtml) {
        String subjectLetter = "Password Recovery";
        try{
            message.setFrom(new InternetAddress(Configuration.getProperty("mail.username")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subjectLetter);
            message.setContent(textHtml, "text/html; charset=utf-8");
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    private boolean sendToTransport(MimeMessage message) {
        try {
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    public Session getSession() {
        String username = Configuration.getProperty("mail.username");
        String password = Configuration.getProperty("mail.password");
        return Session.getDefaultInstance(SERVER_PROPERTIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}