package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.UserDAOObject;

import java.util.List;

public interface UserDAO {
    List<UserDAOObject> getUsers();
    UserDAOObject getUserById(long id);
    UserDAOObject getUserByEmail(String email);
    UserDAOObject getUserByPassword(String password);
    UserDAOObject getUserByRecoveryHash(String recoveryHash);
    List<UserDAOObject> getUserByRoleId(long roleId);
    UserDAOObject addUser(UserDAOObject user);
    UserDAOObject updateUser(UserDAOObject user);
    void deleteUser(UserDAOObject user);
}
