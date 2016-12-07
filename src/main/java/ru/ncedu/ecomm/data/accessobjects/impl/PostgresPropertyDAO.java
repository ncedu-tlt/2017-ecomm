package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.PropertyDAO;
import ru.ncedu.ecomm.data.models.Property;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.utils.DBUtils.closeConnection;
import static ru.ncedu.ecomm.utils.DBUtils.closeStatement;


public class PostgresPropertyDAO implements PropertyDAO {

    @Override
    public List<Property> getProperty() {
        List<Property> properties = new ArrayList<>();

        Statement stmt = null;
        Connection con = null;

        try {
            con = DBUtils.getConnection();
            stmt =  con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT property_id, value FROM properties");
            while (rs.next()) {
                Property property = new Property();
                property.setId(rs.getLong("property_id"));
                property.setValue(rs.getString("value"));

                properties.add(property);

            }
        }catch(SQLException e) {
                throw new RuntimeException(e);
            }
            finally{
                closeStatement(stmt);
                closeConnection(con);
            }
        return properties;
    }

    @Override
    public Property getPropertyById(long id) {

        PreparedStatement stmt = null;
        Connection con = null;

        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement("SELECT property_id, value FROM properties WHERE property_id =?");
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                Property property = new Property();
                property.setId(rs.getLong("property_id"));
                property.setValue(rs.getString("value"));

                return property;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeStatement(stmt);
            closeConnection(con);
        }
        return null;
    }

    @Override
    public Property addProperty(Property property) {
        return null;
    }

    @Override
    public void deleteProperty(Property property) {

    }

    @Override
    public Property updateProperty(Property property) {
        return null;
    }
}
