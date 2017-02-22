package ru.ncedu.ecomm.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtils {

    private static final int OFFSET = 0;
    private static final int SIGNUM = 1;
    private static final int BITES_COUNT = 32;

    public static String getMd5Digest(String string) {
        String md5Password = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(string.getBytes(), OFFSET, string.length());
            BigInteger bigInt = new BigInteger(SIGNUM, md5.digest());
            md5Password = bigInt.toString();
            while (md5Password.length() < BITES_COUNT) {
                md5Password = "0" + md5Password;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Password;
    }
}
