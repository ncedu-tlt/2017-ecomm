package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.data.models.Characteristic;
import ru.ncedu.ecomm.data.models.CharacteristicValue;
import ru.ncedu.ecomm.servlets.models.CharacteristicGroupModel;
import ru.ncedu.ecomm.servlets.models.CharacteristicModel;

import java.util.List;

public class CharacteristicGroupModelBuilder {
    private long productId;
    private String characteristicGroupName;
    private List<CharacteristicModel> characteristics;

    public CharacteristicGroupModelBuilder() {
    }

    public CharacteristicGroupModelBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
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
                productId,
                characteristicGroupName,
                characteristics
        );
    }
}
