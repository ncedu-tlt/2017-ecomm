package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.User;

public class UserNameService {

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
            userName = user.getEmail();

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
