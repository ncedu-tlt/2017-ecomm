package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.User;

public class UserNameService {

    private static final String DEFAULT_USER_NAME = "Anonymous";

    private static UserNameService instance;

    public static synchronized UserNameService getInstance(){
        if (instance == null) {
            instance = new UserNameService();
        }

        return instance;
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
