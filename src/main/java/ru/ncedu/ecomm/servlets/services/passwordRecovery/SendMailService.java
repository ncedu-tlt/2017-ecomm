package ru.ncedu.ecomm.servlets.services.passwordRecovery;

import ru.ncedu.ecomm.Configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailService {

    private final Properties SERVER_PROPERTIES = configServer();
    private final static String MAIL_USERNAME = Configuration.getProperty("mail.username");
    private final static String MAIL_PASSWORD = Configuration.getProperty("mail.password");
    private final static String SUBJECT_LETTER = "Password Recovery";
    private final static String MAIL_TYPE = "text/html; charset=utf-8";

    private SendMailService(){
    }

    private static SendMailService instance;
    public static synchronized SendMailService getInstance(){
        if(instance == null){
            instance = new SendMailService();
        }
        return instance;
    }

    private Properties configServer() {
        Properties serverProperties = System.getProperties();
        serverProperties.setProperty("mail.smtp.auth", "true");
        serverProperties.setProperty("mail.smtp.starttls.enable", "true");
        serverProperties.setProperty("mail.smtp.host", "smtp.gmail.com");
        serverProperties.setProperty("mail.smtp.port", "587");
        serverProperties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        return serverProperties;
    }

    boolean isSentLetterToEmail(String toEmail, String textHtml) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        return setMimeMessage(message, toEmail, textHtml) && isSentMessage(message);
    }

    private boolean setMimeMessage(MimeMessage message, String toEmail, String textHtml) {
        try{
            message.setFrom(new InternetAddress(MAIL_USERNAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(SUBJECT_LETTER);
            message.setContent(textHtml, MAIL_TYPE);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    private boolean isSentMessage(MimeMessage message) {
        try {
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    private Session getSession() {
        return Session.getDefaultInstance(SERVER_PROPERTIES, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_USERNAME, MAIL_PASSWORD);
            }
        });
    }
}