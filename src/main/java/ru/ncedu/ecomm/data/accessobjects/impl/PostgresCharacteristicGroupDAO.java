package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.CharacteristicGroupDAO;
import ru.ncedu.ecomm.data.models.CharacteristicGroup;
import ru.ncedu.ecomm.data.models.builders.CharacteristicGroupBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresCharacteristicGroupDAO implements CharacteristicGroupDAO {

    @Override
    public List<CharacteristicGroup> getCharacteristicGroup() {

        List<CharacteristicGroup> characteristicGroups = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT characteristic_group_id, " +
                            "name " +
                            "FROM public.characteristic_groups"
            );
            while (resultSet.next()) {
                CharacteristicGroup characteristicGroup = new CharacteristicGroupBuilder()
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCharacteristicGroupName(resultSet.getString("name"))
                        .build();

                characteristicGroups.add(characteristicGroup);
            }
            return characteristicGroups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CharacteristicGroup getCharacteristicGroupById(long characteristicGroupId) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT characteristic_group_id, " +
                             "name " +
                             "FROM public.characteristic_groups " +
                             "WHERE characteristic_group_id = ?"
             )) {
            statement.setLong(1, characteristicGroupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new CharacteristicGroupBuilder()
                        .setCharacteristicGroupId(resultSet.getLong("characteristic_group_id"))
                        .setCharacteristicGroupName(resultSet.getString("name"))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public CharacteristicGroup addCharacteristicGroup(CharacteristicGroup characteristicGroup) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT  INTO public.characteristic_groups " +
                             "(characteristic_group_id, name) " +
                             "VALUES (?, ?) " +
                             "RETURNING characteristic_group_id"
             )) {
            statement.setLong(1, characteristicGroup.getCharacteristicGroupId());
            statement.setString(2, characteristicGroup.getCharacteristicGroupName());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                characteristicGroup.setCharacteristicGroupId(statement.getResultSet().getLong("characteristic_group_id"));
            }

            return characteristicGroup;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public CharacteristicGroup updateCharacteristicGroup(CharacteristicGroup characteristicGroup) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.characteristic_groups " +
                             "SET name = ? " +
                             "WHERE characteristic_group_id = ?"
             )) {
            statement.setString(1, characteristicGroup.getCharacteristicGroupName());
            statement.setLong(2, characteristicGroup.getCharacteristicGroupId());
            statement.execute();

            return characteristicGroup;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCharacteristicGroup(CharacteristicGroup characteristicGroup) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE  FROM  public.characteristic_groups " +
                             "WHERE characteristic_group_id = ?"
             )) {
            statement.setLong(1, characteristicGroup.getCharacteristicGroupId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
