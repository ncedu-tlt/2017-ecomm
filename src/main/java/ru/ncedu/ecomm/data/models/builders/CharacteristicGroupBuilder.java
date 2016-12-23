package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.CharacteristicGroup;

public class CharacteristicGroupBuilder {
    private long characteristicGroupId;
    private String  characteristicGroupName;

    public CharacteristicGroupBuilder() {
    }

    public CharacteristicGroupBuilder setCharacteristicGroupId(long characteristicGroupId){
        this.characteristicGroupId = characteristicGroupId;

        return this;
    }
    public CharacteristicGroupBuilder setCharacteristicGroupName(String characteristicGroupName){
        this.characteristicGroupName = characteristicGroupName;

        return this;
    }
    public CharacteristicGroup build(){
        return new CharacteristicGroup(
                characteristicGroupId,
                characteristicGroupName);
    }
}
