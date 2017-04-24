package ru.ncedu.ecomm.data.accessobjects.impl;

import org.apache.log4j.Logger;
import ru.ncedu.ecomm.data.accessobjects.CharacteristicGroupDAO;
import ru.ncedu.ecomm.data.models.dao.CharacteristicGroupDAOObject;
import ru.ncedu.ecomm.data.models.dao.builders.CharacteristicGroupDAOObjectBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresCharacteristicGroupDAO implements CharacteristicGroupDAO {
    private static final Logger LOG = Logger.getLogger(PostgresCharacteristicGroupDAO.class);

    @Override
    public List<CharacteristicGroupDAOObject> getCharacteristicGroup() {

        List<CharacteristicGroupDAOObject> characteristicGroups = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  characteristic_group_id,\n" +
                            "  name\n" +
                            "FROM public.characteristic_groups"
            );
            while (resultSet.next()) {
                CharacteristicGroupDAOObject characteristicGroup = new CharacteristicGroupDAOObjectBuilder()
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCharacteristicGroupName(resultSet.getString("name"))
                        .build();

                characteristicGroups.add(characteristicGroup);
            }

            LOG.info(null);
            return characteristicGroups;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public CharacteristicGroupDAOObject getCharacteristicGroupById(long characteristicGroupId) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  characteristic_group_id,\n" +
                             "  name\n" +
                             "FROM public.characteristic_groups\n" +
                             "WHERE characteristic_group_id = ?"
             )) {
            statement.setLong(1, characteristicGroupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                LOG.info(null);
                return new CharacteristicGroupDAOObjectBuilder()
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCharacteristicGroupName(resultSet.getString("name"))
                        .build();
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public CharacteristicGroupDAOObject addCharacteristicGroup(CharacteristicGroupDAOObject characteristicGroup) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.characteristic_groups\n" +
                             "(characteristic_group_id, name)\n" +
                             "VALUES (?, ?)\n" +
                             "RETURNING characteristic_group_id"
             )) {
            statement.setLong(1, characteristicGroup.getCharacteristicGroupId());
            statement.setString(2, characteristicGroup.getCharacteristicGroupName());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                characteristicGroup.setCharacteristicGroupId(statement.getResultSet().getLong("characteristic_group_id"));
            }

            LOG.info(null);
            return characteristicGroup;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public CharacteristicGroupDAOObject updateCharacteristicGroup(CharacteristicGroupDAOObject characteristicGroup) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.characteristic_groups\n" +
                             "SET name = ?\n" +
                             "WHERE characteristic_group_id = ?"
             )) {
            statement.setString(1, characteristicGroup.getCharacteristicGroupName());
            statement.setLong(2, characteristicGroup.getCharacteristicGroupId());
            statement.execute();

            LOG.info(null);
            return characteristicGroup;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCharacteristicGroup(CharacteristicGroupDAOObject characteristicGroup) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.characteristic_groups\n" +
                             "WHERE characteristic_group_id = ?"
             )) {
            statement.setLong(1, characteristicGroup.getCharacteristicGroupId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
