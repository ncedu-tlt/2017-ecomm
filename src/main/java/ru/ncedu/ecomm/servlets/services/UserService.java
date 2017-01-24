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

    public String getUserName(User user) {
        String userName;

        if (user.getFirstName().isEmpty() && user.getLastName().isEmpty()) {
            userName = DEFAULT_USER_NAME;

        } else if (user.getFirstName().isEmpty()) {
            userName = user.getLastName();

        } else if (user.getLastName().isEmpty()) {
            userName = user.getFirstName();

        } else {
            userName = user.getFirstName() + " " + user.getLastName();
        }
        return userName;
    }
}
