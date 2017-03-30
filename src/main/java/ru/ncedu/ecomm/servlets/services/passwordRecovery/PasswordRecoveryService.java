package ru.ncedu.ecomm.servlets.services.passwordRecovery;

import ru.ncedu.ecomm.data.models.UserDAOObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class PasswordRecoveryService {

    private PasswordRecoveryService() {
    }

    private static PasswordRecoveryService instance;

    public static synchronized PasswordRecoveryService getInstance() {
        if (instance == null) {
            instance = new PasswordRecoveryService();
        }
        return instance;
    }

    public String getAnswer(String toEmail, String contextPath) {
        UserDAOObject userByEmail = getDAOFactory().getUserDAO().getUserByEmail(toEmail);
        if (userByEmail == null)
            return "ErrorEmailNotFound";
        sendingLetterToEmail(userByEmail);
        return sendMailToUser(userByEmail, contextPath);
    }

    private void sendingLetterToEmail(UserDAOObject userByEmail) {
        String recoveryHash = getRecoveryHashAfterChecking();
        userByEmail.setRecoveryHash(recoveryHash);
    }

    private String getRecoveryHashAfterChecking() {
        String recoveryHash = generateRecoveryHash();
        UserDAOObject userByHash = getDAOFactory().getUserDAO()
                .getUserByRecoveryHash(recoveryHash);
        while(userByHash != null){
            recoveryHash = generateRecoveryHash();
            userByHash = getDAOFactory().getUserDAO()
                    .getUserByRecoveryHash(recoveryHash);
        }
        return recoveryHash;
    }

    private String generateRecoveryHash() {
        List<Integer> uniqueHashCollection = new ArrayList<>();
        addHashToCollection(uniqueHashCollection);
        return getHashFromCollection(uniqueHashCollection);
    }

    private void addHashToCollection(List<Integer> uniqueHash) {
        final int MAX_HASH = 10;
        final int MAX_NUMBER = 9;
        Random random = new Random();
        while (uniqueHash.size() < MAX_HASH) {
            uniqueHash.add(random.nextInt(MAX_NUMBER));
        }
    }

    private String getHashFromCollection(List<Integer> uniqueHashCollection) {
        StringBuilder recoveryHash = new StringBuilder();
        recoveryHash.append("");
        for (Integer hash : uniqueHashCollection) {
            recoveryHash.append(hash);
        }
        return recoveryHash.toString();
    }

    private String sendMailToUser(UserDAOObject user, String contextPath) {
        String textHTML = getTextHtml(user.getEmail(), user.getRecoveryHash(), contextPath);
        PasswordRecoveryService.getInstance().addRecoveryHashToUser(user, user.getRecoveryHash());
        return SendingMailService.getInstance().isSentLetterToEmail(user.getEmail(), textHTML) ?
                "SuccessSend"
                : "ErrorSend";
    }

    private void addRecoveryHashToUser(UserDAOObject user, String recoveryHash) {
        user.setRecoveryHash(recoveryHash);
        getDAOFactory().getUserDAO().updateUser(user);
    }

    private String getTextHtml(String toEmail, String recoveryHash, String contextPath) {
        return "<p>Please change your password in here:</p>" +
                "<a href='https://" + contextPath + "/passwordChange?email="
                + toEmail + "&recoveryHash=" + recoveryHash + "'>Change Password</a>";
    }
}
