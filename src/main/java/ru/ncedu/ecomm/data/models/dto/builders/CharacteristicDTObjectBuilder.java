package ru.ncedu.ecomm.data.models.dto.builders;

import ru.ncedu.ecomm.data.models.dto.CharacteristicDTObject;

public class CharacteristicDTObjectBuilder {
    private long characteristicId;
    private String name;
    private String value;

    public CharacteristicDTObjectBuilder() {
    }

    public CharacteristicDTObjectBuilder setCharacteristicId(long characteristicId) {
        this.characteristicId = characteristicId;

        return this;
    }

    public CharacteristicDTObjectBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public CharacteristicDTObjectBuilder setValue(String value) {
        this.value = value;

        return this;
    }

    public CharacteristicDTObject build() {
        return new CharacteristicDTObject(
                characteristicId,
                name,
                value
        );
    }
}
