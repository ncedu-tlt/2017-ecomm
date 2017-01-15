package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.RoleDAO;
import ru.ncedu.ecomm.data.models.Role;
import ru.ncedu.ecomm.data.models.builders.RoleBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresRoleDAO implements RoleDAO {

    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();

        try(Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  role_id,\n" +
                            "  name\n" +
                            "FROM roles");
            while (resultSet.next()) {
                Role role = new RoleBuilder()
                        .setId(resultSet.getLong("role_id"))
                        .setName(resultSet.getString("name"))
                        .build();

                roles.add(role);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return roles;
    }

    @Override
    public Role getRoleById(long id) {

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT\n" +
                            "  role_id,\n" +
                            "  name\n" +
                            "FROM roles\n" +
                            "WHERE role_id = ?"
            )) {

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Role role = new RoleBuilder()
                        .setName(resultSet.getString("name"))
                        .setId(resultSet.getLong("role_id"))
                        .build();
                return role;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
