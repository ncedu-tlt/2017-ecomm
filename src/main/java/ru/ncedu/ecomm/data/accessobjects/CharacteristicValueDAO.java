package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.CharacteristicValue;

import java.util.List;

public interface CharacteristicValueDAO {
    List<CharacteristicValue> getCharacteristicValue();

    CharacteristicValue addCharacteristicValue();

    CharacteristicValue updateCharacteristicValue();

    void deleteCharacteristicValue();
}
