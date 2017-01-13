package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.CharacteristicModel;

public class CharacteristicModelBuilder {
    private String characteristicName;
    private String characteristicValue;

    public CharacteristicModelBuilder() {

    }

    public CharacteristicModelBuilder setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;

        return this;
    }

    public CharacteristicModelBuilder setCahracteristicValue(String cahracteristicValue) {
        this.characteristicValue = cahracteristicValue;

        return this;
    }

    public CharacteristicModel build(){
        return new CharacteristicModel(
                characteristicName,
                characteristicValue
        );
    }
}
