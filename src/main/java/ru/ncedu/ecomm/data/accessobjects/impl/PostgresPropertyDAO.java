package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.PropertyDAO;
import ru.ncedu.ecomm.data.models.Property;
import ru.ncedu.ecomm.data.models.builders.PropertyBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PostgresPropertyDAO implements PropertyDAO {

    @Override
    public List<Property> getProperties() {
        List<Property> properties = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  property_id,\n" +
                            "  value\n" +
                            "FROM properties");
            while (resultSet.next()) {
                Property property = new PropertyBuilder()
                        .setPropertyId(resultSet.getString("property_id"))
                        .setValue(resultSet.getString("value"))
                        .build();

                properties.add(property);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    @Override
    public Property getPropertyById(String id) {

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
                return new PropertyBuilder()
                        .setPropertyId(resultSet.getString("property_id"))
                        .setValue(resultSet.getString("value"))
                        .build();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Property addProperty(Property property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO properties (\n" +
                             "  value,\n" +
                             "  property_id)\n" +
                             "VALUES (?, ?)")) {

            statement.setString(1, property.getValue());
            statement.setString(2, property.getId());
            statement.execute();

            return property;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProperty(Property property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM properties\n" +
                             "WHERE property_id = ?")) {

            statement.setString(1, property.getId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Property updateProperty(Property property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE properties\n" +
                             "SET value = ?\n" +
                             "WHERE property_id = ?")) {
            statement.setString(1, property.getValue());
            statement.setString(2, property.getId());
            statement.execute();

           return property;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Property> getSocials() {
        List<Property> properties = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, value\n" +
                            "FROM (SELECT\n" +
                            "        lower(VALUE) || 'Url' AS concatid,\n" +
                            "        value AS id\n" +
                            "      FROM properties\n" +
                            "      WHERE property_id ~ '(social\\d)') AS id\n" +
                            "  INNER JOIN (SELECT\n" +
                            "                value,\n" +
                            "                property_id AS concatId\n" +
                            "              FROM\n" +
                            "                properties\n" +
                            "              WHERE property_id IN (SELECT lower(VALUE) || 'Url' AS id\n" +
                            "                                    FROM properties\n" +
                            "                                    WHERE property_id ~ '(social\\d)')) AS values\n" +
                            "    ON id.concatid = values.concatId");
            while (resultSet.next()) {
                Property property = new PropertyBuilder()
                        .setPropertyId(resultSet.getString("id"))
                        .setValue(resultSet.getString("value"))
                        .build();

                properties.add(property);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    @Override
    public Property addSocial(Property property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO properties (property_id, value)\n" +
                             "VALUES (\n" +
                             "  (SELECT 'social' || CAST(SUBSTRING(property_id FROM 7) AS BIGINT) + 1\n" +
                             "   FROM properties\n" +
                             "   WHERE property_id ~ '(social\\d)'\n" +
                             "   ORDER BY property_id DESC\n" +
                             "   LIMIT 1), ?), (lower(?)||'Url', ?)")) {

            statement.setString(1, property.getId());
            statement.setString(2, property.getId());
            statement.setString(3, property.getValue());

            statement.execute();

            return property;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSocial(Property property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM properties\n" +
                             "WHERE value = ?, value = ?")) {

            statement.setString(1, property.getId());
            statement.setString(2, property.getValue());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Property updateSocial(Property property) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE properties\n" +
                             "   SET value = ?\n" +
                             "WHERE property_id = lower(?)||'Url'")) {
            statement.setString(1, property.getValue());
            statement.setString(2, property.getId());
            statement.execute();

            return property;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
