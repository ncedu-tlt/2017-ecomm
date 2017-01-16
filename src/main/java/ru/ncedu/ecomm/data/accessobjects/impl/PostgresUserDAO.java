package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.UserDAO;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.data.models.builders.UserBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserDAO implements UserDAO {
    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  user_id,\n" +
                            "  role_id,\n" +
                            "  first_name,\n" +
                            "  last_name,\n" +
                            "  password,\n" +
                            "  phone,\n" +
                            "  email,\n" +
                            "  registration_date\n" +
                            "FROM users");
            while (resultSet.next()) {
                User user = new UserBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .build();

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User getUserById(long id) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  user_id,\n" +
                             "  role_id,\n" +
                             "  first_name,\n" +
                             "  last_name,\n" +
                             "  password,\n" +
                             "  phone,\n" +
                             "  email,\n" +
                             "  registration_date\n" +
                             "FROM users\n" +
                             "WHERE user_id = ?")) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new UserBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  user_id,\n" +
                             "  role_id,\n" +
                             "  first_name,\n" +
                             "  last_name,\n" +
                             "  password,\n" +
                             "  phone,\n" +
                             "  email,\n" +
                             "  registration_date,\n" +
                             "  recovery_hash\n" +
                             "FROM users\n" +
                             "WHERE email = ?")) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){

                return new UserBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setRecoveryHash(resultSet.getString("recovery_hash"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> getUserByRoleId(long roleId) {
        List<User> users = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  user_id,\n" +
                             "  role_id,\n" +
                             "  first_name,\n" +
                             "  last_name,\n" +
                             "  password,\n" +
                             "  phone,\n" +
                             "  email,\n" +
                             "  registration_date\n" +
                             "FROM users\n" +
                             "WHERE role_id = ?")) {


            statement.setLong(1, roleId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new UserBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .build();

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User getUserByPassword(String password) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  user_id,\n" +
                             "  role_id,\n" +
                             "  first_name,\n" +
                             "  last_name,\n" +
                             "  password,\n" +
                             "  phone,\n" +
                             "  email,\n" +
                             "  registration_date,\n" +
                             "  recovery_hash\n" +
                             "FROM users\n" +
                             "WHERE password = ?")) {

            statement.setString(1, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){

                return new UserBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public User addUser(User user) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users\n" +
                             "(role_id,\n" +
                             " first_name,\n" +
                             " last_name,\n" +
                             " password,\n" +
                             " phone,\n" +
                             " email,\n" +
                             " registration_date)\n" +
                             "VALUES (?, ?, ?, ?, ?, ?, current_timestamp)\n" +
                             "RETURNING user_id")) {

            statement.setLong(1, user.getRoleId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getEmail());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                return user;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE users\n" +
                             "SET role_id     = ?,\n" +
                             "  first_name    = ?,\n" +
                             "  last_name     = ?,\n" +
                             "  password      = ?,\n" +
                             "  phone         = ?,\n" +
                             "  email         = ?,\n" +
                             "  recovery_hash = ?\n" +
                             "WHERE user_id = ?")) {
            statement.setLong(1, user.getRoleId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getRecoveryHash());
            statement.setLong(8, user.getId());
            statement.execute();

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(User user) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM users\n" +
                             "WHERE user_id = ?")) {
            statement.setLong(1, user.getId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
