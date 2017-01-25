package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Characteristic;

import java.util.List;

public interface CharacteristicDAO {
    List<Characteristic> getCharacteristic();

    List<Characteristic> getCharacteristicByCategoryIdAndGroupId(long categoryId, long groupId);

    List<Characteristic> getCharacteristicByGroupId(long characteristicGroupId);

    Characteristic addCharacteristic(Characteristic characteristic);

    Characteristic getCharacteristicById(long characteristicId);

    Characteristic updateCharacteristic(Characteristic characteristic);

    void deleteCharacteristic(Characteristic characteristic);

    List<Characteristic> getFilterableCharacteristicsByCategoryId(long categoryId);

}
