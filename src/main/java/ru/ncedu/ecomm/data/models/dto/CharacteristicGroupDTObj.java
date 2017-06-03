package ru.ncedu.ecomm.data.models.dto;

import java.util.List;

public class CharacteristicGroupDTObj {
    private long characteristicGroupId;
    private String characteristicGroupName;
    private List<CharacteristicDTObject> characteristics;

    public CharacteristicGroupDTObj() {
    }

    public CharacteristicGroupDTObj(long characteristicGroupId, String characteristicGroupName, List<CharacteristicDTObject> characteristics) {
        this.characteristicGroupId = characteristicGroupId;
        this.characteristicGroupName = characteristicGroupName;
        this.characteristics = characteristics;
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

    public List<CharacteristicDTObject> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicDTObject> characteristics) {
        this.characteristics = characteristics;
    }
}
