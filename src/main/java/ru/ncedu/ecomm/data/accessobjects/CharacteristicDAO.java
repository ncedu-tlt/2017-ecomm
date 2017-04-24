package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.CharacteristicDAOObject;

import java.util.List;

public interface CharacteristicDAO {
    List<CharacteristicDAOObject> getCharacteristic();

    List<CharacteristicDAOObject> getCharacteristicByCategoryIdAndGroupId(long categoryId, long groupId);

    List<CharacteristicDAOObject> getCharacteristicByGroupId(long characteristicGroupId);

    CharacteristicDAOObject addCharacteristic(CharacteristicDAOObject characteristic);

    CharacteristicDAOObject getCharacteristicById(long characteristicId);

    CharacteristicDAOObject updateCharacteristic(CharacteristicDAOObject characteristic);

    void deleteCharacteristic(CharacteristicDAOObject characteristic);

    List<CharacteristicDAOObject> getFilterableCharacteristicsByCategoryId(long categoryId);

}
