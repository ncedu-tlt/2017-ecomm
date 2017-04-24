package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.UserDAOObject;
import ru.ncedu.ecomm.data.models.dto.UserDTOObject;

import java.util.List;

public interface UserDAO {
    List<UserDAOObject> getUsers();
    List<UserDTOObject> getUsersManagement();
    UserDTOObject getUserManagementById(long id);
    UserDAOObject getUserById(long id);
    UserDAOObject getUserByEmail(String email);
    UserDAOObject getUserByPassword(String password);
    UserDAOObject getUserByRecoveryHash(String recoveryHash);
    List<UserDAOObject> getUserByRoleId(long roleId);
    UserDAOObject addUser(UserDAOObject user);
    UserDAOObject updateUser(UserDAOObject user);
    void deleteUser(UserDAOObject user);
}
