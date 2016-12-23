package ru.ncedu.ecomm.data.models;


public class CharacteristicGroup {
    private long characteristicGroupId;
    private String characteristicGroupName;

    public CharacteristicGroup() {
    }

    public CharacteristicGroup(long characteristicGroupId, String characteristicGroupName) {
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
