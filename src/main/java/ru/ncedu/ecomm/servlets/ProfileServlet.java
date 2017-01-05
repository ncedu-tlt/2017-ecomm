package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;


@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = getUserIdFromUrl(req.getQueryString());
        if (changeProfile(userId, req)) {
            req.setAttribute("answer", "Profile was changed.");
            req.getRequestDispatcher("/views/pages/profile.jsp?user_id=" + userId).forward(req, resp);
        } else {
            req.setAttribute("answer", "Nothing change.");
            req.getRequestDispatcher("/views/pages/profile.jsp?user_id=" + userId).forward(req, resp);
        }
    }

    private long getUserIdFromUrl(String query) {
        String[] parametersFromUrl = query.split("=");
        long userId = Long.parseLong(parametersFromUrl[1]);
        return userId;
    }

    private boolean changeProfile(long userId, HttpServletRequest req) {
        String firstName = getFirstName(req.getParameter("firstName"), userId);
        String lastName = getLastName(req.getParameter("lastName"), userId);
        String email = getEmail(req.getParameter("email"), userId);
        String password = getPassword(req.getParameter("password"), userId);

        if (firstName == null && lastName == null && email == null && password == null) {
            return false;
        } else {
            updateUser(userId, firstName, lastName, email, password);
            return true;
        }
    }

    private String getFirstName(String firstName, long userId) {
        if (!firstName.trim().isEmpty()) {
            User userById = getDAOFactory().getUserDAO().getUserById(userId);
            return userById.getFirstName() != firstName ?
                    firstName :
                    null;
        } else {
            return null;
        }
    }

    private String getLastName(String lastName, long userId) {
        if (!lastName.trim().isEmpty()) {
            User userById = getDAOFactory().getUserDAO().getUserById(userId);
            return userById.getLastName() != lastName ?
                    lastName :
                    null;
        } else {
            return null;
        }
    }

    private String getEmail(String email, long userId) {
        String regPattern = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        Pattern patternEmailValidation = Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(email);
        if (matcher.find()) {
            User userById = getDAOFactory().getUserDAO().getUserById(userId);
            return userById.getEmail() != email ?
                    email :
                    null;
        } else {
            return null;
        }
    }

    private String getPassword(String password, long userId) {
        if (!password.trim().isEmpty()) {
            User userById = getDAOFactory().getUserDAO().getUserById(userId);
            return userById.getPassword() != password ?
                    password :
                    null;
        } else {
            return null;
        }
    }

    private void updateUser(long userId, String firstName, String lastName, String email, String password) {
        User user = getDAOFactory().getUserDAO().getUserById(userId);
        if (firstName != null) {
            user.setFirstName(firstName);
        }
        if (lastName != null) {
            user.setLastName(lastName);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (password != null) {
            user.setPassword(password);
        }
        getDAOFactory().getUserDAO().updateUser(user);
    }
}
