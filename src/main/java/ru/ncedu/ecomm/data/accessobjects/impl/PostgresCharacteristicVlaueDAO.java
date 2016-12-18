package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.CharacteristicValueDAO;
import ru.ncedu.ecomm.data.models.CharacteristicValue;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresCharacteristicVlaueDAO implements CharacteristicValueDAO{

    @Override
    public List<CharacteristicValue> getCharacteristicValue() {
        List<CharacteristicValue> characteristicValues = new ArrayList<>();

        try(Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT characteristic_id, product_id, value" +
                    " FROM public.characteristic_values");
            while (resultSet.next()) {
                CharacteristicValue characteristicValue = new CharacteristicValue();
                characteristicValue.setCharacteristicId(resultSet.getLong("characteristic_id"));
                characteristicValue.setProductId(resultSet.getLong("product_id"));
                characteristicValue.setCharacteristicValue(resultSet.getString("value"));

                characteristicValues.add(characteristicValue);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return characteristicValues;
    }

    @Override
    public CharacteristicValue addCharacteristicValue() {
        return null;
    }

    @Override
    public CharacteristicValue updateCharacteristicValue() {
        return null;
    }

    @Override
    public void deleteCharacteristicValue() {

    }
}
