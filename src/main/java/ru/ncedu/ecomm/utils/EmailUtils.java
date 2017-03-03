package ru.ncedu.ecomm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {
    private static final String CHECK = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

    public static boolean checkEmail(String email){

        Pattern patternEmailValidation = Pattern.compile(CHECK
                ,Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(email);

        return matcher.find();
    }
}

