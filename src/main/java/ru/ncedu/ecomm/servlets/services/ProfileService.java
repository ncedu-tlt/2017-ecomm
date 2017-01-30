package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ProfileService {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final User userProfile;

    public ProfileService(HttpServletRequest req, long userId) {
        this.firstName = getFirstName(req.getParameter("firstName"), userId);
        this.lastName = getLastName(req.getParameter("lastName"), userId);
        this.email = getEmail(req.getParameter("email"), userId);
        this.password = getPassword(req.getParameter("password"), userId);
        this.userProfile = getUserProfile(userId);
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

    public User getUserProfile(long userId) {
        User userProfile = getDAOFactory().getUserDAO().getUserById(userId);
        return userProfile;
    }

    public User changeProfile(User user){
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }
}
