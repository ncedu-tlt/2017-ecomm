package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.UserDAOObject;
import ru.ncedu.ecomm.data.models.dto.UserDTOObject;

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
    void deleteUser(long userId);
    List<UserDTOObject> getUsersForManagement();
    UserDTOObject getUserForManagementById(long id);
    UserDTOObject updateUserForManagement(UserDTOObject user);
}
