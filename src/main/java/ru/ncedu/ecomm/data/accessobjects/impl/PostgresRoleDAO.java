package ru.ncedu.ecomm.data.accessobjects.impl;

import org.apache.log4j.Logger;
import ru.ncedu.ecomm.data.accessobjects.RoleDAO;
import ru.ncedu.ecomm.data.models.dao.RoleDAOObject;
import ru.ncedu.ecomm.data.models.dao.builders.RoleDAOObjectBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresRoleDAO implements RoleDAO {
    private static final Logger LOG  = Logger.getLogger(PostgresRoleDAO.class);

    @Override
    public List<RoleDAOObject> getRoles() {
        List<RoleDAOObject> roles = new ArrayList<>();

        try(Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  role_id,\n" +
                            "  name\n" +
                            "FROM roles");
            while (resultSet.next()) {
                RoleDAOObject role = new RoleDAOObjectBuilder()
                        .setId(resultSet.getLong("role_id"))
                        .setName(resultSet.getString("name"))
                        .build();

                roles.add(role);
            }

            LOG.info(null);

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return roles;
    }

    @Override
    public RoleDAOObject getRoleById(long id) {

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
                RoleDAOObject role = new RoleDAOObjectBuilder()
                        .setName(resultSet.getString("name"))
                        .setId(resultSet.getLong("role_id"))
                        .build();

                LOG.info(null);
                return role;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }
}
