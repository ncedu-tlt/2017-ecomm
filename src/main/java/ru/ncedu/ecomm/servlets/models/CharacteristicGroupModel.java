package ru.ncedu.ecomm.servlets.models;

import ru.ncedu.ecomm.data.models.Characteristic;
import ru.ncedu.ecomm.data.models.CharacteristicValue;

import java.util.List;

public class CharacteristicGroupModel {
    private long productId;
    private String characteristicGroupName;
    private List<CharacteristicModel> characteristics;

    public CharacteristicGroupModel() {
    }

    public CharacteristicGroupModel(long productId,
                                    String characteristicGroupName,
                                    List<CharacteristicModel> characteristics) {
        this.productId = productId;
        this.characteristicGroupName = characteristicGroupName;
        this.characteristics = characteristics;
    }

    public String getCharacteristicGroupName() {
        return characteristicGroupName;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setCharacteristicGroupName(String characteristicGroupName) {
        this.characteristicGroupName = characteristicGroupName;
    }

    public List<CharacteristicModel> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicModel> characteristics) {
        this.characteristics = characteristics;
    }
}
