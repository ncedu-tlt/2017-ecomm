package ru.ncedu.ecomm.servlets;


import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;


@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService.getInstance().redirectToLoginIfNeeded(req, resp);
        req.getRequestDispatcher("/views/pages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService.getInstance().redirectToLoginIfNeeded(req, resp);
        HttpSession authorization = req.getSession();
        if (checkOnEmpty(req)) {
            long userId = getUserIdFromSession(authorization);
            changeProfile(userId, req);
            req.setAttribute("answer", "Profile was changed.");
            req.getRequestDispatcher("/views/pages/profile.jsp?user_id=" + userId).forward(req, resp);
        } else {
            req.setAttribute("answer", "Nothing to change.");
            req.getRequestDispatcher("/views/pages/profile.jsp").forward(req, resp);
        }
    }

    private boolean checkOnEmpty(HttpServletRequest req) {
        List<String> userParameters = new ArrayList<>();
        userParameters.add(req.getParameter("firstName"));
        userParameters.add(req.getParameter("lastName"));
        userParameters.add(req.getParameter("email"));
        userParameters.add(req.getParameter("password"));

        for (String parameter : userParameters) {
            if (!parameter.trim().isEmpty())
                return true;
        }
        return false;
    }

    private long getUserIdFromSession(HttpSession authorization) {
        String parametersFromSession = authorization.getAttribute("user_id").toString();
        long userId = Long.parseLong(parametersFromSession);
        return userId;
    }

    private void changeProfile(long userId, HttpServletRequest req) {
        User user = getDAOFactory().getUserDAO().getUserById(userId);

        String firstName = getFirstName(req.getParameter("firstName"), userId);
        String lastName = getLastName(req.getParameter("lastName"), userId);
        String email = getEmail(req.getParameter("email"), userId);
        String password = getPassword(req.getParameter("password"), userId);

        user = changeUser(user, firstName, lastName, email, password);

        getDAOFactory().getUserDAO().updateUser(user);
    }

    private User changeUser(User user, String firstName, String lastName, String email, String password) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    private String getFirstName(String firstName, long userId) {
        User userById = getDAOFactory().getUserDAO().getUserById(userId);
        if (!firstName.trim().isEmpty()) {
            return userById.getFirstName() != firstName ?
                    firstName :
                    userById.getFirstName();
        } else {
            return userById.getFirstName();
        }
    }

    private String getLastName(String lastName, long userId) {
        User userById = getDAOFactory().getUserDAO().getUserById(userId);
        if (!lastName.trim().isEmpty()) {
            return userById.getLastName() != lastName ?
                    lastName :
                    userById.getLastName();
        } else {
            return userById.getLastName();
        }
    }

    private String getEmail(String email, long userId) {
        User userById = getDAOFactory().getUserDAO().getUserById(userId);
        String regPattern = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        Pattern patternEmailValidation = Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(email);
        if (matcher.find()) {
            return userById.getEmail() != email ?
                    email :
                    userById.getEmail();
        } else {
            return userById.getEmail();
        }
    }

    private String getPassword(String password, long userId) {
        User userById = getDAOFactory().getUserDAO().getUserById(userId);
        if (!password.trim().isEmpty()) {
            return userById.getPassword() != password ?
                    password :
                    userById.getPassword();
        } else {
            return userById.getPassword();
        }
    }
}
