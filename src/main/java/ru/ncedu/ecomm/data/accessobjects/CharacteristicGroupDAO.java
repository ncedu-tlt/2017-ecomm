package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.CharacteristicGroup;

import java.util.List;

public interface CharacteristicGroupDAO {
    List<CharacteristicGroup> getCharacteristicGroup();

    CharacteristicGroup getCharacteristicGroupById(long characteristicGroupId);

    CharacteristicGroup addCharacteristicGroup(CharacteristicGroup characteristicGroup);

    CharacteristicGroup updateCharacteristicGroup(CharacteristicGroup characteristicGroup);

    void deleteCharacteristicGroup(CharacteristicGroup characteristicGroup);
}
