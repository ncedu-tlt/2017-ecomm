package ru.ncedu.ecomm.data.accessobjects.impl;

import org.apache.log4j.Logger;
import ru.ncedu.ecomm.data.accessobjects.UserDAO;
import ru.ncedu.ecomm.data.models.UserDAOObject;
import ru.ncedu.ecomm.data.models.builders.UserDAOObjectBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresUserDAO implements UserDAO {
    private static final Logger LOG = Logger.getLogger(PostgresUserDAO.class);

    @Override
    public List<UserDAOObject> getUsers() {
        List<UserDAOObject> users = new ArrayList<>();

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
                            "  registration_date," +
                            "  recovery_hash," +
                            "  user_avatar\n" +
                            "FROM users");
            while (resultSet.next()) {
                UserDAOObject user = new UserDAOObjectBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRecoveryHash(resultSet.getString("recovery_hash"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setUserAvatar(resultSet.getString("user_avatar"))
                        .build();

                users.add(user);
            }
            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public UserDAOObject getUserById(long id) {

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
                             "  recovery_hash,\n" +
                             "  user_avatar\n" +
                             "FROM users\n" +
                             "WHERE user_id = ?")) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LOG.info(null);
                return new UserDAOObjectBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setRecoveryHash(resultSet.getString("recovery_hash"))
                        .setUserAvatar(resultSet.getString("user_avatar"))
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public UserDAOObject getUserByEmail(String email) {
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
                             "  recovery_hash,\n" +
                             "  user_avatar\n" +
                             "FROM users\n" +
                             "WHERE email = ?")) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                LOG.info(null);
                return new UserDAOObjectBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setRecoveryHash(resultSet.getString("recovery_hash"))
                        .setUserAvatar(resultSet.getString("user_avatar"))
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<UserDAOObject> getUserByRoleId(long roleId) {
        List<UserDAOObject> users = new ArrayList<>();

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
                             "  recovery_hash,\n" +
                             "  user_avatar\n" +
                             "FROM users\n" +
                             "WHERE role_id = ?")) {


            statement.setLong(1, roleId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserDAOObject user = new UserDAOObjectBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRecoveryHash(resultSet.getString("recovery_hash"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setUserAvatar(resultSet.getString("user_avatar"))
                        .build();

                users.add(user);
            }
            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public UserDAOObject getUserByPassword(String password) {
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
                             "  recovery_hash,\n" +
                             "  registration_date,\n" +
                             "  user_avatar\n" +
                             "FROM users\n" +
                             "WHERE password = ?")) {

            statement.setString(1, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                LOG.info(null);
                return new UserDAOObjectBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRecoveryHash(resultSet.getString("recovery_hash"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setUserAvatar(resultSet.getString("user_avatar"))
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public UserDAOObject getUserByRecoveryHash(String recoveryHash) {
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
                             "  recovery_hash,\n" +
                             "  registration_date,\n" +
                             "  user_avatar\n" +
                             "FROM users\n" +
                             "WHERE recovery_hash = ?")) {

            statement.setString(1, recoveryHash);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                LOG.info(null);
                return new UserDAOObjectBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setRoleId(resultSet.getLong("role_id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setPassword(resultSet.getString("password"))
                        .setPhone(resultSet.getString("phone"))
                        .setEmail(resultSet.getString("email"))
                        .setRecoveryHash(resultSet.getString("recovery_hash"))
                        .setRegistrationDate(resultSet.getDate("registration_date"))
                        .setUserAvatar(resultSet.getString("user_avatar"))
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public UserDAOObject addUser(UserDAOObject user) {

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

                LOG.info(null);
                return user;
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public UserDAOObject updateUser(UserDAOObject user) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.users\n" +
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

            LOG.info(null);
            return user;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(UserDAOObject user) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM users\n" +
                             "WHERE user_id = ?")) {
            statement.setLong(1, user.getId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
