package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    User getUserById(long id);
    User getUserByEmail(String email);
    User getUserByPassword(String password);
    List<User> getUserByRoleId(long roleId);
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
}
