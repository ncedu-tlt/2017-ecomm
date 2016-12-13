package ru.ncedu.ecomm.data.accessobjects.impl;

import com.sun.jersey.api.NotFoundException;
import ru.ncedu.ecomm.data.accessobjects.UserDAO;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.utils.DBUtils.closeConnection;
import static ru.ncedu.ecomm.utils.DBUtils.closeStatement;

public class PostgresUserDAO implements UserDAO {
    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT user_id, role_id, login, first_name, last_name, password, phone, email, registration_date FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setRoleId(resultSet.getLong("role_id"));
                user.setLogin(resultSet.getString("login"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setRegistrationDate(resultSet.getDate("registration_date"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return users;
    }

    @Override
    public User getUserById(long id) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement("SELECT user_id, role_id, login, first_name, last_name, password, phone, email, registration_date FROM users WHERE user_id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setRoleId(resultSet.getLong("role_id"));
                user.setLogin(resultSet.getString("login"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setRegistrationDate(resultSet.getDate("registration_date"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NotFoundException f){
            f.printStackTrace();
        } finally{
            closeConnection(connection);
            closeStatement(statement);
        }
        return null;
    }

    @Override
    public List<User> getUserByRoleId(long roleId) {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement("SELECT user_id, role_id, login, first_name, last_name, password, phone, email, registration_date FROM users WHERE role_id = ?");
            statement.setLong(1, roleId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong("user_id"), resultSet.getLong("role_id"), resultSet.getString("login"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email"),
                        resultSet.getDate("registration_date"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NotFoundException f){
            f.printStackTrace();
        } finally {
            closeConnection(connection);
            closeStatement(statement);
        }
        return users;
    }

    @Override
    public User addUser(User user) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement("INSERT INTO users (role_id, login, first_name, last_name, password, phone, email, registration_date)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, current_timestamp)" +
                    "RETURNING user_id, role_id, login, first_name, last_name, password, phone, email, registration_date");
            statement.setLong(1, user.getRoleId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getPhone());
            statement.setString(7, user.getEmail());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                User newUser = new User( resultSet.getLong("user_id"),
                resultSet.getLong("role_id"),
                resultSet.getString("login"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("password"),
                resultSet.getString("phone"),
                resultSet.getString("email"),
                resultSet.getDate("registration_date"));
                return newUser;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalStateException i){
            i.printStackTrace();
        }finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement("UPDATE users " +
                    "SET role_id = ?, login = ?, first_name = ?, last_name = ?, password = ?, phone = ?, email = ?" +
                    "WHERE user_id = ?" +
                    "RETURNING user_id, role_id, login, first_name, last_name, password, phone, email, registration_date");
            statement.setLong(1, user.getRoleId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getPhone());
            statement.setString(7, user.getEmail());
            statement.setLong(8, user.getId());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                User updateUser = new User(resultSet.getLong("user_id"), resultSet.getLong("role_id"), resultSet.getString("login"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("password"), resultSet.getString("phone"), resultSet.getString("email"),
                        resultSet.getDate("registration_date"));
                return updateUser;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalStateException i){
            i.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public void deleteUser(User user) {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement("DELETE FROM users" +
                    " WHERE user_id = ?");
            statement.setLong(1, user.getId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }
}
