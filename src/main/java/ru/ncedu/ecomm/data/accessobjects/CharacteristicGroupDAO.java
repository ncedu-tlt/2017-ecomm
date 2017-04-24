package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.CharacteristicGroupDAOObject;

import java.util.List;

public interface CharacteristicGroupDAO {
    List<CharacteristicGroupDAOObject> getCharacteristicGroup();

    CharacteristicGroupDAOObject getCharacteristicGroupById(long characteristicGroupId);

    CharacteristicGroupDAOObject addCharacteristicGroup(CharacteristicGroupDAOObject characteristicGroup);

    CharacteristicGroupDAOObject updateCharacteristicGroup(CharacteristicGroupDAOObject characteristicGroup);

    void deleteCharacteristicGroup(CharacteristicGroupDAOObject characteristicGroup);
}
