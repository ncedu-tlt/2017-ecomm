package ru.ncedu.ecomm.servlets.models;

import ru.ncedu.ecomm.data.models.Characteristic;
import ru.ncedu.ecomm.data.models.CharacteristicValue;

import java.util.List;

public class CharacteristicGroupModel {
    private String characteristicGroupName;
    private List<Characteristic> characteristics;
    private List<CharacteristicValue> characteristicValues;

    public CharacteristicGroupModel() {
    }

    public CharacteristicGroupModel(String characteristicGroupName,
                                    List<Characteristic> characteristics,
                                    List<CharacteristicValue> characteristicValues) {
        this.characteristicGroupName = characteristicGroupName;
        this.characteristics = characteristics;
        this.characteristicValues = characteristicValues;
    }

    public String getCharacteristicGroupName() {
        return characteristicGroupName;
    }

    public void setCharacteristicGroupName(String characteristicGroupName) {
        this.characteristicGroupName = characteristicGroupName;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public List<CharacteristicValue> getCharacteristicValues() {
        return characteristicValues;
    }

    public void setCharacteristicValues(List<CharacteristicValue> characteristicValues) {
        this.characteristicValues = characteristicValues;
    }
}
