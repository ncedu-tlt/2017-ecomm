package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.CharacteristicDAO;
import ru.ncedu.ecomm.data.models.Characteristic;
import ru.ncedu.ecomm.data.models.builders.CharacteristicBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class PostgresCharacteristicDAO implements CharacteristicDAO {
    private static final Logger LOG = Logger.getLogger(PostgresCharacteristicDAO.class);

    @Override
    public List<Characteristic> getCharacteristic() {
        List<Characteristic> characteristics = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  characteristic_id,\n" +
                            "  category_id,\n" +
                            "  name,\n" +
                            "  characteristic_group_id\n" +
                            "FROM public.characteristics"
            );
            while (resultSet.next()) {
                Characteristic characteristic = new CharacteristicBuilder()
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicName(resultSet.getString("name"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .build();

                characteristics.add(characteristic);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return characteristics;
    }

    @Override
    public List<Characteristic> getCharacteristicByGroupId(long characteristicGroupId) {
        List<Characteristic> characteristics = new ArrayList<>();

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT\n" +
                            "  characteristic_id,\n" +
                            "  category_id,\n" +
                            "  name,\n" +
                            "  characteristic_group_id\n" +
                            "FROM public.characteristics\n" +
                            "WHERE characteristic_group_id = ?"

            )){

            statement.setLong(1, characteristicGroupId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Characteristic characteristic = new CharacteristicBuilder()
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicName(resultSet.getString("name"))
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .build();

                characteristics.add(characteristic);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }

        return characteristics;
    }

    @Override
    public List<Characteristic> getCharacteristicByCategoryIdAndGroupId(long categoryId, long groupId) {
        List<Characteristic> characteristics = new ArrayList<>();

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT\n" +
                            "  characteristic_id,\n" +
                            "  category_id,\n" +
                            "  name,\n" +
                            "  characteristic_group_id\n" +
                            "FROM public.characteristics\n" +
                            "WHERE category_id = ?\n" +
                            "      AND characteristic_group_id = ?"

            )){
            statement.setLong(1, categoryId);
            statement.setLong(2, groupId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Characteristic characteristic = new CharacteristicBuilder()
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicName(resultSet.getString("name"))
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .build();

                characteristics.add(characteristic);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }

        return characteristics;
    }

    @Override
    public Characteristic addCharacteristic(Characteristic characteristic) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.characteristics\n" +
                             "(characteristic_id,\n" +
                             " category_id,\n" +
                             " name,\n" +
                             " characteristic_group_id)\n" +
                             "VALUES (?, ?, ?, ?)\n" +
                             "RETURNING characteristic_id"
             )) {
            statement.setLong(1, characteristic.getCharacteristicId());
            statement.setLong(2, characteristic.getCategoryId());
            statement.setString(3, characteristic.getCharacteristicName());
            statement.setLong(4, characteristic.getCharacteristicGroupId());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                characteristic.setCharacteristicId(statement.getResultSet().getLong("characteristic_id"));
            }

            LOG.info(null);
            return characteristic;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Characteristic getCharacteristicById(long characteristicId) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  characteristic_id,\n" +
                             "  category_id,\n" +
                             "  name,\n" +
                             "  characteristic_group_id\n" +
                             "FROM public.characteristics\n" +
                             "WHERE characteristic_id = ?"
             )) {

            statement.setLong(1, characteristicId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                LOG.info(null);
                return new CharacteristicBuilder()
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicName(resultSet.getString("name"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .build();
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Characteristic updateCharacteristic(Characteristic characteristic) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.characteristics\n" +
                             "SET category_id           = ?,\n" +
                             "  name                    = ?,\n" +
                             "  characteristic_group_id = ?\n" +
                             "WHERE characteristic_id = ?"
             )) {
            statement.setLong(1, characteristic.getCategoryId());
            statement.setString(2, characteristic.getCharacteristicName());
            statement.setLong(3, characteristic.getCharacteristicGroupId());
            statement.setLong(4, characteristic.getCharacteristicId());
            statement.execute();

            LOG.info(null);
            return characteristic;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCharacteristic(Characteristic characteristic) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.characteristics\n" +
                             "WHERE characteristic_id = ?"
             )) {
            statement.setLong(1, characteristic.getCharacteristicId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Characteristic> getFilterableCharacteristicsByCategoryId(long categoryId) {
        List<Characteristic> characteristics = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  characteristic_id,\n" +
                             "  category_id,\n" +
                             "  name,\n" +
                             "  characteristic_group_id\n" +
                             "FROM public.characteristics\n" +
                             "where filterable = TRUE and category_id = " +
                             "(WITH RECURSIVE recParentId (category_id, parent_id) AS\n" +
                             "(SELECT category_id, parent_id\n" +
                             " FROM categories\n" +
                             " WHERE category_id = ?\n" +
                             " UNION\n" +
                             " SELECT ct.category_id, ct.parent_id\n" +
                             " FROM categories ct INNER JOIN recParentId\n" +
                             "     ON (recParentId.parent_id = ct.category_id))\n" +
                             "SELECT category_id\n" +
                             "FROM recParentId ORDER BY category_id LIMIT 1)")) {
            statement.setLong(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                characteristics.add(new CharacteristicBuilder()
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicName(resultSet.getString("name"))
                        .setCategoryId(resultSet.getLong("category_id"))
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
