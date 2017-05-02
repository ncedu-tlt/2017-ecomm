package ru.ncedu.ecomm.data.accessobjects.impl;

import org.apache.log4j.Logger;
import ru.ncedu.ecomm.data.accessobjects.PropertyDAO;
import ru.ncedu.ecomm.data.models.dao.PropertyDAOObject;
import ru.ncedu.ecomm.data.models.dao.builders.PropertyDAOObjectBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PostgresPropertyDAO implements PropertyDAO {
    private static final Logger LOG  = Logger.getLogger(PostgresPropertyDAO.class);

    @Override
    public List<PropertyDAOObject> getProperties() {
        List<PropertyDAOObject> properties = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  property_id,\n" +
                            "  value\n" +
                            "FROM properties");
            while (resultSet.next()) {
                PropertyDAOObject property = new PropertyDAOObjectBuilder()
                        .setPropertyId(resultSet.getString("property_id"))
                        .setValue(resultSet.getString("value"))
                        .build();

                properties.add(property);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return properties;
    }

    @Override
    public PropertyDAOObject getPropertyById(String id) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  property_id,\n" +
                             "  value\n" +
                             "FROM properties\n" +
                             "WHERE property_id = ?")) {
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                LOG.info(null);
                return new PropertyDAOObjectBuilder()
                        .setPropertyId(resultSet.getString("property_id"))
                        .setValue(resultSet.getString("value"))
                        .build();

            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public PropertyDAOObject addProperty(PropertyDAOObject property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO properties (\n" +
                             "  value,\n" +
                             "  property_id)\n" +
                             "VALUES (?, ?)")) {

            statement.setString(1, property.getValue());
            statement.setString(2, property.getId());
            statement.execute();

            LOG.info(null);
            return property;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProperty(PropertyDAOObject property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM properties\n" +
                             "WHERE property_id = ?")) {

            statement.setString(1, property.getId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public PropertyDAOObject updateProperty(PropertyDAOObject property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE properties\n" +
                             "SET value = ?\n" +
                             "WHERE property_id = ?")) {
            statement.setString(1, property.getValue());
            statement.setString(2, property.getId());
            statement.execute();

            LOG.info(null);
            return property;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public PropertyDAOObject updateValueProperty(PropertyDAOObject property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE properties\n" +
                             "SET value = ?\n" +
                             "WHERE property_id = ?")) {
            statement.setString(1, property.getValue());
            statement.setString(2, property.getId());
            statement.execute();

            LOG.info(null);
            return property;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public PropertyDAOObject updateIdProperty(PropertyDAOObject property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE properties\n" +
                             "SET property_id = ?\n" +
                             "WHERE value = ?")) {
            statement.setString(1, property.getId());
            statement.setString(2, property.getValue());
            statement.execute();

            LOG.info(null);
            return property;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}


