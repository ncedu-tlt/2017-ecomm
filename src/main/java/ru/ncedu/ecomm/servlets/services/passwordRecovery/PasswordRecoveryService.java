package ru.ncedu.ecomm.servlets.services.passwordRecovery;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordRecoveryService {
    private final HttpServletRequest req;
    private final String toEmail;
    private final String fromEmail;
    private final String recoveryHash;
    private final String textHTML;


    public PasswordRecoveryService(HttpServletRequest req) {
        this.req = req;
        this.toEmail = req.getParameter("email");
        this.fromEmail = "netcracker.ecomm@gmail.ru";
        this.recoveryHash = generateRecoveryHash();
        this.textHTML = "<p>Please change your password in here:</p>" +
        "<a href='https://ncedu-ecomm-dev.herokuapp.com/passwordChange?email=" + toEmail + "&recoveryHash=" + recoveryHash + "'>Change Password</a>";
    }


    public String getRecoveryHash() {
        return recoveryHash;
    }

    public String getToEmail() {
        return toEmail;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public String getTextHTML() {
        return textHTML;
    }

    private String generateRecoveryHash() {
        List<Integer> uniqueHash = new ArrayList<>();
        Random random = new Random();
        final int MAX_HASH = 10;
        final int MAX_NUMBER = 9;
        while (uniqueHash.size() < MAX_HASH) {
            uniqueHash.add(random.nextInt(MAX_NUMBER));
        }

        String recoveryHash = "";
        for (Integer hash : uniqueHash) {
            recoveryHash += hash;
        }

        return recoveryHash;
    }
}
