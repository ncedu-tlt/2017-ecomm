package ru.ncedu.ecomm.servlets.services.passwordRecovery;

import ru.ncedu.ecomm.data.models.dao.UserDAOObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class PasswordRecoveryService {

    private final static String ERROR_FOUND_EMAIL = "ErrorEmailNotFound";
    private final static String SUCCESS_SEND = "SuccessSend";
    private final static String ERROR_SEND = "ErrorSend";
    private final static int MAX_HASH = 10;
    private final static int MAX_NUMBER = 9;

    private PasswordRecoveryService() {
    }

    private static PasswordRecoveryService instance;

    public static synchronized PasswordRecoveryService getInstance() {
        if (instance == null) {
            instance = new PasswordRecoveryService();
        }
        return instance;
    }

    public String sendMailToUser(String toEmail, String contextPath) {
        UserDAOObject userByEmail = getDAOFactory().getUserDAO().getUserByEmail(toEmail);
        if (userByEmail == null)
            return ERROR_FOUND_EMAIL;
        userByEmail.setRecoveryHash(getRecoveryHash());
        return sendMailToUser(userByEmail, contextPath);
    }


    private String getRecoveryHash() {
        String recoveryHash;
        UserDAOObject userByHash;
        do {
            recoveryHash = generateRecoveryHash();
            userByHash = getDAOFactory().getUserDAO().getUserByRecoveryHash(recoveryHash);
        } while (userByHash != null);
        return recoveryHash;
    }

    private String generateRecoveryHash() {
        List<Integer> uniqueHashCollection = new ArrayList<>();
        addHashToCollection(uniqueHashCollection);
        return getHashFromCollection(uniqueHashCollection);
    }

    private void addHashToCollection(List<Integer> uniqueHash) {
        Random random = new Random();
        while (uniqueHash.size() < MAX_HASH) {
            uniqueHash.add(random.nextInt(MAX_NUMBER));
        }
    }

    private String getHashFromCollection(List<Integer> uniqueHashCollection) {
        StringBuilder recoveryHash = new StringBuilder(MAX_HASH);
        for (int hash : uniqueHashCollection) {
            recoveryHash.append(hash);
        }
        return recoveryHash.toString();
    }

    private String sendMailToUser(UserDAOObject user, String contextPath) {
        String textHTML = getTextHtml(user.getEmail(), user.getRecoveryHash(), contextPath);
        getDAOFactory().getUserDAO().updateUser(user);
        return SendMailService.getInstance().isSentLetterToEmail(user.getEmail(), textHTML) ?
                SUCCESS_SEND
                : ERROR_SEND;
    }

    private String getTextHtml(String toEmail, String recoveryHash, String contextPath) {
        return "<p>Please change your password in here:</p>" +
                "<a href='" + contextPath + "/passwordChange?email="
                + toEmail + "&recoveryHash=" + recoveryHash + "'>Change Password</a>";
    }
}
