package ru.ncedu.ecomm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationUtils {
    private static final String CHECK_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String CHECK_PASSWORD = "(?=.*[1-9a-zа-яёA-ZА-ЯЁ@$_]).{3,20}";
    private static final String CHECK_NAME = "^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_\\.]{1,20}$";

    public static boolean checkEmail(String email) {
        Pattern patternEmailValidation = Pattern.compile(CHECK_EMAIL
                , Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(email);

        return matcher.find();
    }

    public static boolean checkPassword(String password) {
        Pattern pattern = Pattern.compile(CHECK_PASSWORD);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean checkName(String name) {
        Pattern pattern = Pattern.compile(CHECK_NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}

