package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.CharacteristicGroupDAOObject;

public class CharacteristicGroupDAOObjectBuilder {
    private long characteristicGroupId;
    private String  characteristicGroupName;

    public CharacteristicGroupDAOObjectBuilder() {
    }

    public CharacteristicGroupDAOObjectBuilder setCharacteristicGroupId(long characteristicGroupId){
        this.characteristicGroupId = characteristicGroupId;

        return this;
    }
    public CharacteristicGroupDAOObjectBuilder setCharacteristicGroupName(String characteristicGroupName){
        this.characteristicGroupName = characteristicGroupName;

        return this;
    }
    public CharacteristicGroupDAOObject build(){
        return new CharacteristicGroupDAOObject(
                characteristicGroupId,
                characteristicGroupName);
    }
}
