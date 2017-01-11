package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.data.models.Characteristic;
import ru.ncedu.ecomm.data.models.CharacteristicValue;
import ru.ncedu.ecomm.servlets.models.CharacteristicGroupModel;

import java.util.List;

public class CharacteristicGroupModelBuilder {
    private String characteristicGroupName;
    private List<Characteristic> characteristics;
    private List<CharacteristicValue> characteristicValues;

    public CharacteristicGroupModelBuilder() {
    }

    public CharacteristicGroupModelBuilder setCharacteristicGroupName(String characteristicGroupName) {
        this.characteristicGroupName = characteristicGroupName;

        return this;
    }

    public CharacteristicGroupModelBuilder setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;

        return this;
    }

    public CharacteristicGroupModelBuilder setCharacteristicValues(List<CharacteristicValue> characteristicValues) {
        this.characteristicValues = characteristicValues;

        return this;
    }

    private CharacteristicGroupModel build(){
        return new CharacteristicGroupModel(
                characteristicGroupName,
                characteristics,
                characteristicValues
        );
    }
}
