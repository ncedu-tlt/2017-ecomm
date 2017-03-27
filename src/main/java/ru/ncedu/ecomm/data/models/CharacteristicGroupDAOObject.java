package ru.ncedu.ecomm.data.models;


public class CharacteristicGroupDAOObject {
    private long characteristicGroupId;
    private String characteristicGroupName;

    public CharacteristicGroupDAOObject() {
    }

    public CharacteristicGroupDAOObject(long characteristicGroupId, String characteristicGroupName) {
        this.characteristicGroupId = characteristicGroupId;
        this.characteristicGroupName = characteristicGroupName;
    }

    public long getCharacteristicGroupId() {
        return characteristicGroupId;
    }

    public void setCharacteristicGroupId(long characteristicGroupId) {
        this.characteristicGroupId = characteristicGroupId;
    }

    public String getCharacteristicGroupName() {
        return characteristicGroupName;
    }

    public void setCharacteristicGroupName(String characteristicGroupName) {
        this.characteristicGroupName = characteristicGroupName;
    }
}
