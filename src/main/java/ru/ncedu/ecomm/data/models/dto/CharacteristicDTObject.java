package ru.ncedu.ecomm.data.models.dto;

public class CharacteristicDTObject {
    private long characteristicId;
    private String name;
    private String value;

    public CharacteristicDTObject() {
    }

    public CharacteristicDTObject(long characteristicId, String name, String value) {
        this.characteristicId = characteristicId;
        this.name = name;
        this.value = value;
    }

    public long getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(long characteristicId) {
        this.characteristicId = characteristicId;
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
