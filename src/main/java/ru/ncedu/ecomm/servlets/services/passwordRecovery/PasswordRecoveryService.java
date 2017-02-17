package ru.ncedu.ecomm.servlets.services.passwordRecovery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordRecoveryService {

    private PasswordRecoveryService(){}

    private static PasswordRecoveryService instance;

    public static synchronized PasswordRecoveryService getInstance(){
        if(instance == null){
            instance = new PasswordRecoveryService();
        }
        return instance;
    }

    public String getRecoveryHash() {
        return createRecoveryHash();
    }

    private String createRecoveryHash() {
        List<Integer> uniqueHash = new ArrayList<>();
        addHashToCollection(uniqueHash);
        return getHash(uniqueHash);
    }

    private String getHash(List<Integer> uniqueHash) {
        String recoveryHash = "";
        for (Integer hash : uniqueHash) {
            recoveryHash += hash;
        }
        return recoveryHash;
    }

    private void addHashToCollection(List<Integer> uniqueHash) {
        final int MAX_HASH = 10;
        final int MAX_NUMBER = 9;
        Random random = new Random();
        while (uniqueHash.size() < MAX_HASH) {
            uniqueHash.add(random.nextInt(MAX_NUMBER));
        }
    }
}
