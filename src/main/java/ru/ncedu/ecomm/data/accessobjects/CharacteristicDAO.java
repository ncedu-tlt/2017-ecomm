package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Characteristic;

import java.util.List;

public interface CharacteristicDAO {
    List<Characteristic> getCharacteristic();

    Characteristic addCharacteristic(Characteristic characteristic);



    Characteristic getCharacteristicById(long characteristicId);

    Characteristic updateCharacteristic(Characteristic characteristic);

    void deleteCharacteristic(Characteristic characteristic);

}
