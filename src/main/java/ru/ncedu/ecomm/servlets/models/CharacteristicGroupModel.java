package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class CharacteristicGroupModel {
    private String characteristicGroupName;
    private List<CharacteristicModel> characteristics;

    public CharacteristicGroupModel() {
    }

    public CharacteristicGroupModel(String characteristicGroupName,
                                    List<CharacteristicModel> characteristics) {
        this.characteristicGroupName = characteristicGroupName;
        this.characteristics = characteristics;
    }

    public String getCharacteristicGroupName() {
        return characteristicGroupName;
    }

    public void setCharacteristicGroupName(String characteristicGroupName) {
        this.characteristicGroupName = characteristicGroupName;
    }

    public List<CharacteristicModel> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicModel> characteristics) {
        this.characteristics = characteristics;
    }
}
