package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.CharacteristicValueDAOObject;

import java.util.List;

public interface CharacteristicValueDAO {
    List<CharacteristicValueDAOObject> getCharacteristicValues();

    List<CharacteristicValueDAOObject> getCharacteristicValuesById(long id);

    List<CharacteristicValueDAOObject> getCharacteristicValuesByProductId(long productId);

    CharacteristicValueDAOObject getCharacteristicValueByIdAndProductId(long productId, long characteristicId);

    CharacteristicValueDAOObject addCharacteristicValue(CharacteristicValueDAOObject characteristicValue);

    CharacteristicValueDAOObject updateCharacteristicValue(CharacteristicValueDAOObject characteristicValue);

    void deleteCharacteristicValue(CharacteristicValueDAOObject characteristicValue);

    List<CharacteristicValueDAOObject> getCharacteristicValuesByIdAndProductsCategoryId(long characteristicId, long categoryId);

    }
