package ru.ncedu.ecomm.data.models.dto;

import ru.ncedu.ecomm.data.models.dao.CharacteristicDAOObject;

import java.util.List;

public class CharacteristicGroupDTOObject {
    private long characteristicGroupId;
    private String characteristicGroupName;
    private List<CharacteristicDAOObject> characteristics;

    public CharacteristicGroupDTOObject(){}

    public CharacteristicGroupDTOObject(long characteristicGroupId,
                                        String characteristicGroupName,
                                        List<CharacteristicDAOObject> characteristics) {
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

    public List<CharacteristicDAOObject> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicDAOObject> characteristics) {
        this.characteristics = characteristics;
    }
}
