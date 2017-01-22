package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.CharacteristicModel;

public class CharacteristicModelBuilder {
    private String name;
    private String value;

    public CharacteristicModelBuilder() {

    }

    public CharacteristicModelBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public CharacteristicModelBuilder setValue(String cahracteristicValue) {
        this.value = cahracteristicValue;

        return this;
    }

    public CharacteristicModel build(){
        return new CharacteristicModel(
                name,
                value
        );
    }
}
