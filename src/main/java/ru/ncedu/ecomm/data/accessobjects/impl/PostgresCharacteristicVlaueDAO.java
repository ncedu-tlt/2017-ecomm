package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.CharacteristicValueDAO;
import ru.ncedu.ecomm.data.models.CharacteristicValueDAOObject;
import ru.ncedu.ecomm.data.models.builders.CharacteristicValueDAOObjectBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class PostgresCharacteristicVlaueDAO implements CharacteristicValueDAO {
    private static final Logger LOG = Logger.getLogger(PostgresCharacteristicVlaueDAO.class);

    @Override
    public List<CharacteristicValueDAOObject> getCharacteristicValues() {
        List<CharacteristicValueDAOObject> characteristicValues = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  characteristic_id,\n" +
                            "  product_id,\n" +
                            "  value\n" +
                            "FROM public.characteristic_values");
            while (resultSet.next()) {
                CharacteristicValueDAOObject characteristicValue = new CharacteristicValueDAOObjectBuilder()
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicValue(resultSet.getString("value"))
                        .setProductId(resultSet.getLong("product_id"))
                        .build();

                characteristicValues.add(characteristicValue);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return characteristicValues;
    }

    @Override
    public List<CharacteristicValueDAOObject> getCharacteristicValuesById(long id) {
        List<CharacteristicValueDAOObject> characteristicValuesById = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  characteristic_id,\n" +
                             "  product_id,\n" +
                             "  value\n" +
                             "FROM public.characteristic_values\n" +
                             "WHERE characteristic_id = ?")) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CharacteristicValueDAOObject characteristicValue = new CharacteristicValueDAOObjectBuilder()
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicValue(resultSet.getString("value"))
                        .setProductId(resultSet.getLong("product_id"))
                        .build();

                characteristicValuesById.add(characteristicValue);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return characteristicValuesById;
    }

    @Override
    public List<CharacteristicValueDAOObject> getCharacteristicValuesByProductId(long id) {
        List<CharacteristicValueDAOObject> characteristicValuesByProductId = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  characteristic_id,\n" +
                             "  product_id,\n" +
                             "  value\n" +
                             "FROM public.characteristic_values\n" +
                             "WHERE product_id = ?")) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CharacteristicValueDAOObject characteristicValue = new CharacteristicValueDAOObjectBuilder()
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicValue(resultSet.getString("value"))
                        .setProductId(resultSet.getLong("product_id"))
                        .build();

                characteristicValuesByProductId.add(characteristicValue);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return characteristicValuesByProductId;
    }

    @Override
    public CharacteristicValueDAOObject getCharacteristicValueByIdAndProductId(long productId, long characteristicId) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  characteristic_id,\n" +
                             "  product_id,\n" +
                             "  value\n" +
                             "FROM public.characteristic_values\n" +
                             "WHERE product_id = ?\n" +
                             "      AND characteristic_id = ?")) {
            statement.setLong(1, productId);
            statement.setLong(2, characteristicId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                LOG.info(null);
                return new CharacteristicValueDAOObjectBuilder()
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicValue(resultSet.getString("value"))
                        .setProductId(resultSet.getLong("product_id"))
                        .build();
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }


    @Override
    public CharacteristicValueDAOObject addCharacteristicValue(CharacteristicValueDAOObject characteristicValue) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.characteristic_values\n" +
                             "(product_id,\n" +
                             " value,\n" +
                             " characteristic_id)\n" +
                             "VALUES (?, ?, ?)\n" +
                             "RETURNING characteristic_id")) {
            statement.setLong(1, characteristicValue.getProductId());
            statement.setString(2, characteristicValue.getCharacteristicValue());
            statement.setLong(3, characteristicValue.getCharacteristicId());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                characteristicValue.setCharacteristicId(statement.getResultSet().getLong(1));
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return characteristicValue;
    }

    @Override
    public CharacteristicValueDAOObject updateCharacteristicValue(CharacteristicValueDAOObject characteristicValue) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.characteristic_values\n" +
                             "SET value = ?\n" +
                             "WHERE characteristic_id = ?\n" +
                             "      AND product_id = ?"
             )) {
            statement.setString(1, characteristicValue.getCharacteristicValue());
            statement.setLong(2, characteristicValue.getCharacteristicId());
            statement.setLong(3, characteristicValue.getProductId());
            statement.execute();

            LOG.info(null);
            return characteristicValue;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCharacteristicValue(CharacteristicValueDAOObject characteristicValue) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.characteristic_values\n" +
                             "WHERE characteristic_id = ?\n" +
                             "      AND product_id = ?"
             )) {
            statement.setLong(1, characteristicValue.getCharacteristicId());
            statement.setLong(2, characteristicValue.getProductId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<CharacteristicValueDAOObject> getCharacteristicValuesByIdAndProductsCategoryId(long characteristicId, long categoryId) {
        List<CharacteristicValueDAOObject> characteristics = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT DISTINCT ON (value)\n" +
                             "    characteristic_id,\n" +
                             "    product_id,\n" +
                             "    value\n" +
                             "    FROM public.characteristic_values\n" +
                             "    WHERE characteristic_id = ?\n" +
                             "    AND product_id IN (SELECT product_id FROM products\n" +
                             "            WHERE category_id IN (\n" +
                             "            SELECT id FROM (WITH RECURSIVE recCategoriesId (id, parent_id) AS\n" +
                             "(SELECT category_id, parent_id\n" +
                             "    FROM categories\n" +
                             "    WHERE category_id = ?\n" +
                             "    UNION\n" +
                             "    SELECT CT.category_id, CT.parent_id\n" +
                             "    FROM categories CT INNER JOIN recCategoriesId ON (recCategoriesId.id = CT.parent_id))\n" +
                             "    SELECT *\n" +
                             "    FROM recCategoriesId) AS id))")) {
            statement.setLong(1, characteristicId);
            statement.setLong(2, categoryId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                characteristics.add(new CharacteristicValueDAOObjectBuilder()
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicValue(resultSet.getString("value"))
                        .setProductId(resultSet.getLong("product_id"))
                        .build());

            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return characteristics;
    }

}



