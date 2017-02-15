package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

    private static final String DEFAULT_USER_NAME = "Anonymous";

    private UserService() {
    }

    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void redirectToLoginIfNeeded(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            request.getRequestDispatcher("/views/pages/login.jsp").forward(request, response);
        }
    }

    public void redirectIfNotAllowed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        long userRoleId = Long.parseLong(session.getAttribute("userRoleId").toString());
        if (userRoleId != 1){ //TODO: что есть 1?
            request.getRequestDispatcher("/home").include(request, response);
        }
    }

    public long getCurrentUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirectToLoginIfNeeded(request, response); //TODO: а это тут зачем?
        HttpSession session = request.getSession();
        return Long.parseLong(session.getAttribute("userId").toString());
    }

    public String getUserName(User user) {
        String userName;

        if (nameIsEmpty(user.getFirstName()) && nameIsEmpty(user.getLastName())) {
            userName = DEFAULT_USER_NAME;

        } else if (nameIsEmpty(user.getFirstName())){
            userName = user.getLastName();

        } else if (nameIsEmpty(user.getLastName())){
            userName = user.getFirstName();

        } else {
            userName = user.getFirstName() + " " + user.getLastName();
        }
        return userName;
    }

    public String md5DigestPassword(String password){
        String md5Password = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes(), 0, password.length());
            BigInteger bigInt = new BigInteger(1, md5.digest());
            md5Password = bigInt.toString();
            while (md5Password.length() < 32){
                md5Password = "0" + md5Password;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Password;
    }

    //TODO: не возникала ли мысль, что данный метод способен на большее?
    private boolean nameIsEmpty(String name) {
        return name == null || name.isEmpty();
    }
}

