package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.CharacteristicValue;

public class CharacteristicValueBuilder {
    private long characteristicId;
    private long productId;
    private String characteristicValue;

    public CharacteristicValueBuilder(){

    }

    public CharacteristicValueBuilder setCharacteristicId(long characteristicId){
        this.characteristicId = characteristicId;

        return this;
    }

    public CharacteristicValueBuilder setProductId(long productId){
        this.productId = productId;

        return this;
    }

    public CharacteristicValueBuilder setCharacteristicValue(String characteristicValue){
        this.characteristicValue = characteristicValue;

        return this;
    }

    public CharacteristicValue build(){
        return new CharacteristicValue(
                characteristicId,
                productId,
                characteristicValue);
    }
}
