package ru.ncedu.ecomm.servlets.models;

public class CharacteristicModel {
    private String characteristicName;
    private String characteristicValue;

    public CharacteristicModel() {
    }

    public CharacteristicModel(String characteristicName,
                               String characteristicValue) {
        this.characteristicName = characteristicName;
        this.characteristicValue = characteristicValue;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

    public String getCharacteristicValue() {
        return characteristicValue;
    }

    public void setCharacteristicValue(String characteristicValue) {
        this.characteristicValue = characteristicValue;
    }
}
