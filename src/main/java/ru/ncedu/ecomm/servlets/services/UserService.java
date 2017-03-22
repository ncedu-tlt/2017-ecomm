package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

    public Boolean isUserAuthorized(HttpServletRequest request) throws ServletException, IOException {
        Boolean userInSystem = true;
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            userInSystem = false;
        }
        return userInSystem;
    }

    public Boolean redirectIfNotAllowed(HttpServletRequest request, long role) throws ServletException, IOException {
        HttpSession session = request.getSession();
        long userRoleId = Long.parseLong(session.getAttribute("userRoleId").toString());
        return userRoleId == role;
    }

    public long getCurrentUserId(HttpServletRequest request) throws ServletException, IOException {
        HttpSession session = request.getSession();
        return Long.parseLong(session.getAttribute("userId").toString());
    }

    public String getUserName(User user) {
        String userName;

        if (nameIsEmpty(user.getFirstName()) && nameIsEmpty(user.getLastName())) {
            userName = DEFAULT_USER_NAME;

        } else if (nameIsEmpty(user.getFirstName())) {
            userName = user.getLastName();

        } else if (nameIsEmpty(user.getLastName())) {
            userName = user.getFirstName();

        } else {
            userName = user.getFirstName() + " " + user.getLastName();
        }
        return userName;
    }

    //TODO: не возникала ли мысль, что данный метод способен на большее?
    private boolean nameIsEmpty(String name) {
        return name == null || name.isEmpty();
    }
}

