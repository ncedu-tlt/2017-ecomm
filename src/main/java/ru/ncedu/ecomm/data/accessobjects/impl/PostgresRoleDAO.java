package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.RoleDAO;
import ru.ncedu.ecomm.data.models.Role;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.utils.DBUtils.closeConnection;
import static ru.ncedu.ecomm.utils.DBUtils.closeStatement;

public class PostgresRoleDAO implements RoleDAO {

    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();

        Statement stmt = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();

            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select role_id, name from roles");
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getLong("role_id"));
                role.setName(rs.getString("name"));

                roles.add(role);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeStatement(stmt);
            closeConnection(connection);
        }

        return roles;
    }

    @Override
    public Role getRoleById(long id) {

        PreparedStatement stmt = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();

            stmt = connection.prepareStatement("select role_id, name from roles where role_id = ?");
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Role role = new Role();
                role.setId(rs.getLong("role_id"));
                role.setName(rs.getString("name"));

                return role;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeStatement(stmt);
            closeConnection(connection);
        }
        return null;
    }
}
