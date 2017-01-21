package ru.ncedu.ecomm.servlets.models;

import java.util.List;

public class CharacteristicGroupModel {
    private String name;
    private List<CharacteristicModel> characteristics;

    public CharacteristicGroupModel() {
    }

    public CharacteristicGroupModel(String characteristicGroupName,
                                    List<CharacteristicModel> characteristics) {
        this.name = characteristicGroupName;
        this.characteristics = characteristics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CharacteristicModel> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicModel> characteristics) {
        this.characteristics = characteristics;
    }
}
