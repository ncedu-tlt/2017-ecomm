package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.CharacteristicValue;

import java.util.List;

public interface CharacteristicValueDAO {
    List<CharacteristicValue> getCharacteristicValues();

    List<CharacteristicValue> getCharacteristicValuesById(long id);

    List<CharacteristicValue> getCharacteristicValuesByProductId(long productId);

    CharacteristicValue getCharacteristicValueByIdAndProductId(long productId, long characteristicId);

    CharacteristicValue addCharacteristicValue(CharacteristicValue characteristicValue);

    CharacteristicValue updateCharacteristicValue(CharacteristicValue characteristicValue);

    void deleteCharacteristicValue(CharacteristicValue characteristicValue);

    List<CharacteristicValue> getCharacteristicValuesByIdAndProductsCategoryId(long characteristicId, long categoryId);

    }
