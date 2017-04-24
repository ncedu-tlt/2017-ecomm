package ru.ncedu.ecomm.data.models.dao.builders;


import ru.ncedu.ecomm.data.models.dao.CharacteristicDAOObject;

public class CharacteristicDAOObjectBuilder {
    private long characteristicId;
    private long categoryId;
    private String characteristicName;
    private long characteristicGroupId;

    public CharacteristicDAOObjectBuilder(){

    }
    public CharacteristicDAOObjectBuilder setCharacteristicId(long characteristicId){
        this.characteristicId = characteristicId;

        return this;
    }
    public CharacteristicDAOObjectBuilder setCategoryId(long categoryId){
        this.categoryId = categoryId;

        return this;
    }
    public CharacteristicDAOObjectBuilder setCharacteristicName(String characteristicName){
        this.characteristicName = characteristicName;

        return this;
    }
    public CharacteristicDAOObjectBuilder setCharacteristicGroupId(long characteristicGroupId){
        this.characteristicGroupId = characteristicGroupId;

        return this;
    }

    public CharacteristicDAOObject build(){
        return new CharacteristicDAOObject(
                characteristicId,
                categoryId,
                characteristicName,
                characteristicGroupId
        );
    }
}
