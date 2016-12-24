package ru.ncedu.ecomm.data.models.builders;


import ru.ncedu.ecomm.data.models.Characteristic;

public class CharacteristicBuilder {
    private long characteristicId;
    private long categoryId;
    private String characteristicName;
    private long characteristicGroupId;

    CharacteristicBuilder(){

    }
    public CharacteristicBuilder setCharacteristicId(long characteristicId){
        this.characteristicId = characteristicId;

        return this;
    }
    public CharacteristicBuilder setCategoryId(long categoryId){
        this.categoryId = categoryId;

        return this;
    }
    public CharacteristicBuilder setCharacteristicName(String characteristicName){
        this.characteristicName = characteristicName;

        return this;
    }
    public CharacteristicBuilder setCharacteristicGroupId(long characteristicGroupId){
        this.characteristicGroupId = characteristicGroupId;

        return this;
    }

    public Characteristic build(){
        return new Characteristic(
                characteristicId,
                categoryId,
                characteristicName,
                characteristicGroupId
        );
    }
}
