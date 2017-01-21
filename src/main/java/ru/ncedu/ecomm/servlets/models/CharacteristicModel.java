package ru.ncedu.ecomm.servlets.models;

public class CharacteristicModel {
    private String name;
    private String value;

    public CharacteristicModel() {
    }

    public CharacteristicModel(String characteristicName,
                               String characteristicValue) {
        this.name = characteristicName;
        this.value = characteristicValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
