package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.CharacteristicValue;

import java.util.List;

public interface CharacteristicValueDAO {
    List<CharacteristicValue> getCharacteristicValue();

    List<CharacteristicValue> getCharacteristicValueById(long id);

    List<CharacteristicValue> getCharacteristicValueByProductId(long productId);

    List<CharacteristicValue> getCharacteristicValueByIdAndProductId(long productId, long characteristicId);

    CharacteristicValue addCharacteristicValue(CharacteristicValue characteristicValue);

    CharacteristicValue updateCharacteristicValue(CharacteristicValue characteristicValue);

    void deleteCharacteristicValue(CharacteristicValue characteristicValue);
}
