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

    //TODO: а нам действительно нужен такой каскад функций?
    public String getRecoveryHash() {
        return createRecoveryHash();
    }

    private String createRecoveryHash() {
        List<Integer> uniqueHash = new ArrayList<>();
        Random random = new Random();
        addHashToCollection(uniqueHash, random);

        return createNewRecoveryHash(uniqueHash);
    }

    private String createNewRecoveryHash(List<Integer> uniqueHash) {
        String recoveryHash = "";
        for (Integer hash : uniqueHash) {
            recoveryHash += hash;
        }
        return recoveryHash;
    }

    private void addHashToCollection(List<Integer> uniqueHash, Random random) {
        final int MAX_HASH = 10;
        final int MAX_NUMBER = 9;
        while (uniqueHash.size() < MAX_HASH) {
            uniqueHash.add(random.nextInt(MAX_NUMBER));
        }
    }
}
