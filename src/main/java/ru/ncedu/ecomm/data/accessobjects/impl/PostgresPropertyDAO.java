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
    public List<Property> getProperties() {
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

        PreparedStatement stmt = null;
        Connection con = null;

        try {
            con = DBUtils.getConnection();

            stmt = con.prepareStatement("INSERT INTO properties (value)" + " VALUES (?) " +" RETURNING property_id");
            stmt.setString(1, property.getValue());
            stmt.execute();

            int lastPropertyId = 0;
            ResultSet lastProperty = stmt.getResultSet();
            if(lastProperty.next()) {
                lastPropertyId = lastProperty.getInt(1);
            }

            stmt = con.prepareStatement("SELECT property_id, value" + " FROM properties" +" WHERE property_id = ?");
            stmt.setLong(1, lastPropertyId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Property newProperty = new Property(rs.getLong("property_id"), rs.getString("value"));
                return newProperty;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(stmt);
            closeConnection(con);
        }

        return null;
    }

    @Override
    public void deleteProperty(Property property) {

        PreparedStatement stmt = null;
        Connection con= null;

        try {
            con = DBUtils.getConnection();

            stmt = con.prepareStatement("DELETE FROM properties" +" WHERE property_id = ?");
            stmt.setLong(1, property.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(stmt);
            closeConnection(con);
        }

    }

    @Override
    public Property updateProperty(Property property) {

        PreparedStatement stmt = null;
        Connection con = null;

        try {
            con = DBUtils.getConnection();

            stmt = con.prepareStatement("UPDATE properties" + " SET value = ?" + " WHERE property_id = ?");
            stmt.setString(1, property.getValue());
            stmt.execute();

            stmt = con.prepareStatement("SELECT property_id, value" + " FROM properties" + " WHERE category_id = ?");
            stmt.setLong(1, property.getId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Property updatedProperty= new Property(rs.getLong("property_id"), rs.getString("value"));
                return updatedProperty;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(stmt);
            closeConnection(con);
        }

        return null;
    }
}
