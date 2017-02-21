package ru.ncedu.ecomm.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5DigestUtils {

    private static final int OFFSET = 0;
    private static final int SIGNUM = 1;
    private static final int LENGTHOFBITES = 32;

    public static String setMd5Digest(String password) {
        String md5Password = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes(), OFFSET, password.length());
            BigInteger bigInt = new BigInteger(SIGNUM, md5.digest());
            md5Password = bigInt.toString();
            while (md5Password.length() < LENGTHOFBITES) {
                md5Password = "0" + md5Password;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Password;
    }
}
