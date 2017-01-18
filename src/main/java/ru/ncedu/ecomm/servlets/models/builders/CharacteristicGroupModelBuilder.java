package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.CharacteristicGroupModel;
import ru.ncedu.ecomm.servlets.models.CharacteristicModel;

import java.util.List;

public class CharacteristicGroupModelBuilder {
    private String characteristicGroupName;
    private List<CharacteristicModel> characteristics;

    public CharacteristicGroupModelBuilder() {
    }

    public CharacteristicGroupModelBuilder setCharacteristicGroupName(String characteristicGroupName) {
        this.characteristicGroupName = characteristicGroupName;

        return this;
    }

    public CharacteristicGroupModelBuilder setCharacteristics(List<CharacteristicModel> characteristics) {
        this.characteristics = characteristics;

        return this;
    }

    public CharacteristicGroupModel build(){
        return new CharacteristicGroupModel(
                characteristicGroupName,
                characteristics
        );
    }
}
