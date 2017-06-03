package ru.ncedu.ecomm.data.models.dto.builders;

import ru.ncedu.ecomm.data.models.dto.CharacteristicDTObject;
import ru.ncedu.ecomm.data.models.dto.CharacteristicGroupDTObj;

import java.util.List;

public class CharacteristicGroupDTObjBuilder {
    private long characteristicGroupId;
    private String characteristicGroupName;
    private List<CharacteristicDTObject> characteristics;

    public CharacteristicGroupDTObjBuilder() {
    }

    public CharacteristicGroupDTObjBuilder setCharacteristicGroupId(long characteristicGroupId) {
        this.characteristicGroupId = characteristicGroupId;

        return this;
    }

    public CharacteristicGroupDTObjBuilder setCharacteristicGroupName(String characteristicGroupName) {
        this.characteristicGroupName = characteristicGroupName;

        return this;
    }

    public CharacteristicGroupDTObjBuilder setCharacteristics(List<CharacteristicDTObject> characteristics) {
        this.characteristics = characteristics;

        return this;
    }

    public CharacteristicGroupDTObj build() {
        return new CharacteristicGroupDTObj(
                characteristicGroupId,
                characteristicGroupName,
                characteristics
        );
    }
}
