package ru.ncedu.ecomm.data.models.dao.builders;

import ru.ncedu.ecomm.data.models.dao.CharacteristicValueDAOObject;

public class CharacteristicValueDAOObjectBuilder {
    private long characteristicId;
    private long productId;
    private String characteristicValue;

    public CharacteristicValueDAOObjectBuilder(){

    }

    public CharacteristicValueDAOObjectBuilder setCharacteristicId(long characteristicId){
        this.characteristicId = characteristicId;

        return this;
    }

    public CharacteristicValueDAOObjectBuilder setProductId(long productId){
        this.productId = productId;

        return this;
    }

    public CharacteristicValueDAOObjectBuilder setCharacteristicValue(String characteristicValue){
        this.characteristicValue = characteristicValue;

        return this;
    }

    public CharacteristicValueDAOObject build(){
        return new CharacteristicValueDAOObject(
                characteristicId,
                productId,
                characteristicValue);
    }
}
