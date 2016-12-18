package ru.ncedu.ecomm.data.models;

public class CharacteristicGroup {
    private long characteristicGroupId;
    private long characteristicGroupName;

    public CharacteristicGroup() {
    }

    public long getCharacteristicGroupId() {
        return characteristicGroupId;
    }

    public void setCharacteristicGroupId(long characteristicGroupId) {
        this.characteristicGroupId = characteristicGroupId;
    }

    public long getCharacteristicGroupName() {
        return characteristicGroupName;
    }

    public void setCharacteristicGroupName(long characteristicGroupName) {
        this.characteristicGroupName = characteristicGroupName;
    }
}
