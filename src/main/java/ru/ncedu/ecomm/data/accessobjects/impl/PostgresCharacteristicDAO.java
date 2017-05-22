package ru.ncedu.ecomm.data.accessobjects.impl;

import org.apache.log4j.Logger;
import ru.ncedu.ecomm.data.accessobjects.CharacteristicDAO;
import ru.ncedu.ecomm.data.models.dao.CharacteristicDAOObject;
import ru.ncedu.ecomm.data.models.dao.CharacteristicGroupDAOObject;
import ru.ncedu.ecomm.data.models.dao.builders.CharacteristicDAOObjectBuilder;
import ru.ncedu.ecomm.data.models.dao.builders.CharacteristicGroupDAOObjectBuilder;
import ru.ncedu.ecomm.data.models.dto.CharacteristicGroupDTOObject;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresCharacteristicDAO implements CharacteristicDAO {
    private static final Logger LOG = Logger.getLogger(PostgresCharacteristicDAO.class);

    @Override
    public List<CharacteristicDAOObject> getCharacteristic() {
        List<CharacteristicDAOObject> characteristics = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  characteristic_id,\n" +
                            "  category_id,\n" +
                            "  name,\n" +
                            "  characteristic_group_id,\n" +
                            "  filterable\n" +
                            "FROM public.characteristics"
            );
            while (resultSet.next()) {
                CharacteristicDAOObject characteristic = new CharacteristicDAOObjectBuilder()
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicName(resultSet.getString("name"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setFilterable(resultSet.getBoolean("filterable"))
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
    public List<CharacteristicDAOObject> getCharacteristicByGroupId(long characteristicGroupId) {
        List<CharacteristicDAOObject> characteristics = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  characteristic_id,\n" +
                             "  category_id,\n" +
                             "  name,\n" +
                             "  characteristic_group_id\n" +
                             "FROM public.characteristics\n" +
                             "WHERE characteristic_group_id = ?"

             )) {

            statement.setLong(1, characteristicGroupId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CharacteristicDAOObject characteristic = new CharacteristicDAOObjectBuilder()
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
    public List<CharacteristicDAOObject> getCharacteristicByCategoryIdAndGroupId(long categoryId, long groupId) {
        List<CharacteristicDAOObject> characteristics = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  characteristic_id,\n" +
                             "  category_id,\n" +
                             "  name,\n" +
                             "  characteristic_group_id\n," +
                             " filterable\n" +
                             "FROM public.characteristics\n" +
                             "WHERE category_id = ?\n" +
                             "      AND characteristic_group_id = ?"

             )) {
            statement.setLong(1, categoryId);
            statement.setLong(2, groupId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CharacteristicDAOObject characteristic = new CharacteristicDAOObjectBuilder()
                        .setCharacteristicId(resultSet.getLong("characteristic_id"))
                        .setCharacteristicName(resultSet.getString("name"))
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setFilterable(resultSet.getBoolean("filterable"))
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
    public CharacteristicDAOObject addCharacteristic(CharacteristicDAOObject characteristic) {

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
    public CharacteristicDAOObject getCharacteristicById(long characteristicId) {

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
                return new CharacteristicDAOObjectBuilder()
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
    public CharacteristicDAOObject updateCharacteristic(CharacteristicDAOObject characteristic) {

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
    public void deleteCharacteristic(CharacteristicDAOObject characteristic) {

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

    public List<CharacteristicDAOObject> getFilterableCharacteristicsByCategoryId(long categoryId) {
        List<CharacteristicDAOObject> characteristics = new ArrayList<>();

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
                characteristics.add(new CharacteristicDAOObjectBuilder()
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

    @Override
    public List<CharacteristicGroupDTOObject> getCharacteristicsByCategoryIdForManagement(long categoryId) {
        Connection connection = DBUtils.getConnection();
        List<CharacteristicGroupDAOObject> charGroupList = getCharGroupsManagement(connection);
        List<CharacteristicDAOObject> charList = getCharacteristicsManagement(connection, categoryId);
        return getCharGroupsDTOManagement(charGroupList, charList);
    }

    private List<CharacteristicGroupDAOObject> getCharGroupsManagement(Connection connection) {
        List<CharacteristicGroupDAOObject> charGroupList = new ArrayList<>();
        try (PreparedStatement statementCharGroups = connection.prepareStatement(
                "SELECT \n" +
                        "  characteristic_group_id AS char_group_id,\n" +
                        "  name AS char_group_name\n" +
                        "FROM characteristic_groups;"
        )) {
            ResultSet resultSetCharGroups = statementCharGroups.executeQuery();
            while (resultSetCharGroups.next()) {
                CharacteristicGroupDAOObject charGroupBuild = new CharacteristicGroupDAOObjectBuilder()
                        .setCharacteristicGroupId(resultSetCharGroups.getLong("char_group_id"))
                        .setCharacteristicGroupName(resultSetCharGroups.getString("char_group_name"))
                        .build();
                charGroupList.add(charGroupBuild);
            }
            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return charGroupList;
    }

    private List<CharacteristicDAOObject> getCharacteristicsManagement(Connection connection, long categoryId) {
        List<CharacteristicDAOObject> charList = new ArrayList<>();
        try (PreparedStatement statementChars = connection.prepareStatement(
                "SELECT\n" +
                        "  characteristic_id AS chars_id,\n" +
                        "  characteristic_group_id AS char_group_id,\n" +
                        "  name AS chars_name,\n" +
                        "  filterable AS chars_filterable\n" +
                        "FROM characteristics\n" +
                        "WHERE category_id = ?"
        )) {
            statementChars.setLong(1, categoryId);
            ResultSet resultSetChars = statementChars.executeQuery();
            while (resultSetChars.next()) {
                CharacteristicDAOObject charsBuild = new CharacteristicDAOObjectBuilder()
                        .setCharacteristicId(resultSetChars.getLong("chars_id"))
                        .setCharacteristicGroupId(resultSetChars.getLong("char_group_id"))
                        .setCharacteristicName(resultSetChars.getString("chars_name"))
                        .setFilterable(resultSetChars.getBoolean("chars_filterable"))
                        .build();
                charList.add(charsBuild);
            }
            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return charList;
    }

    private List<CharacteristicGroupDTOObject> getCharGroupsDTOManagement
            (List<CharacteristicGroupDAOObject> charGroupList, List<CharacteristicDAOObject> charList){
        List<CharacteristicGroupDTOObject> charGroupsDTO = new ArrayList<>();
        for (CharacteristicGroupDAOObject group : charGroupList) {
            CharacteristicGroupDTOObject charGroupDTO = new CharacteristicGroupDTOObject();
            List<CharacteristicDAOObject> characteristics = new ArrayList<>();
            charGroupDTO.setCharacteristicGroupId(group.getCharacteristicGroupId());
            charGroupDTO.setCharacteristicGroupName(group.getCharacteristicGroupName());
            for (CharacteristicDAOObject characteristic : charList) {
                if (characteristic.getCharacteristicGroupId() == group.getCharacteristicGroupId()) {
                    characteristics.add(characteristic);
                }
            }
            charGroupDTO.setCharacteristics(characteristics);
            charGroupsDTO.add(charGroupDTO);
        }
        return charGroupsDTO;
    }
}
