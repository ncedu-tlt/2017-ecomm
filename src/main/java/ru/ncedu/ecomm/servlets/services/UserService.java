package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public void redirectToLoginIfNeeded(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            request.getRequestDispatcher("/views/pages/login.jsp").forward(request, response);
        }
    }

    public void redirectIfNotAllowed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        long userRoleId = Long.parseLong(session.getAttribute("userRoleId").toString());
        if (userRoleId != 1){
            request.getRequestDispatcher("/home").include(request, response);
        }
    }

    public long getCurrentUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirectToLoginIfNeeded(request, response);
        HttpSession session = request.getSession();
        long userIdFromSession = Long.parseLong(session.getAttribute("userId").toString());
        return userIdFromSession;
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

    private boolean nameIsEmpty(String name) {
        return name == null || name.isEmpty();
    }
}

