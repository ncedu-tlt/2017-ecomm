package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.CharacteristicGroup;

import java.util.List;

public interface CharacteristicGroupDAO {
    List<CharacteristicGroup> getCharacteristicGroup();

    CharacteristicGroup addCharacteristicGroup();

    CharacteristicGroup updateCharacteristicGroup();

    void deleteCharacteristicGroup();
}
