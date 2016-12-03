package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.RoleDAO;
import ru.ncedu.ecomm.data.models.Role;
import static ru.ncedu.ecomm.utils.DBUtils.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresRoleDAO implements RoleDAO {

    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();

        Statement stmt = null;
        Connection connection = null;
        try {
            DataSource ds = getDataSource();

            if ( ds == null ) {
                throw new RuntimeException("Data source not found!");
            }

            connection = ds.getConnection();

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
            DataSource ds = getDataSource();

            if ( ds == null ) {
                throw new RuntimeException("Data source not found!");
            }

            connection = ds.getConnection();

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
