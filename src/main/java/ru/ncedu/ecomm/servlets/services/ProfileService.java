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
        this.firstName = getFirstName(req.getParameter("firstName"));
        this.lastName = getLastName(req.getParameter("lastName"));
        this.email = getEmail(req.getParameter("email"));
        this.password = getPassword(req.getParameter("password"));
        this.userProfile = getDAOFactory().getUserDAO().getUserById(userId);
        System.out.println(userProfile.getEmail());
    }


    private String getFirstName(String firstName) {
        if (!firstName.trim().isEmpty()) {
            return userProfile.getFirstName() != firstName ?
                    firstName :
                    userProfile.getFirstName();
        } else {
            return userProfile.getFirstName();
        }
    }

    private String getLastName(String lastName) {
        if (!lastName.trim().isEmpty()) {
            return userProfile.getLastName() != lastName ?
                    lastName :
                    userProfile.getLastName();
        } else {
            return userProfile.getLastName();
        }
    }

    private String getEmail(String email) {
        String regPattern = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        Pattern patternEmailValidation = Pattern.compile(regPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternEmailValidation.matcher(email);
        if (matcher.find()) {
            return userProfile.getEmail() != email ?
                    email :
                    userProfile.getEmail();
        } else {
            return userProfile.getEmail();
        }
    }

    private String getPassword(String password) {
        if (!password.trim().isEmpty()) {
            return userProfile.getPassword() != password ?
                    password :
                    userProfile.getPassword();
        } else {
            return userProfile.getPassword();
        }
    }

    public User getUserProfile() {
        return this.userProfile;
    }

    public User changeProfile(User user){
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);

        return user;
    }
}
