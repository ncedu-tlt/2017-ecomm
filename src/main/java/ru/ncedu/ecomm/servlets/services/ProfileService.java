package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.User;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ProfileService {

    private ProfileService(){}

    private static ProfileService instance;

    public static synchronized ProfileService getInstance(){
        if(instance == null){
            instance = new ProfileService();
        }
        return instance;
    }

    public User getUserProfileById(long userId){
        return getDAOFactory().getUserDAO().getUserById(userId);
    }
}
