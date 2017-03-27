package ru.ncedu.ecomm.data.models;

public class CharacteristicValueDAOObject {
    private long characteristicId;
    private long productId;
    private String characteristicValue;

    public CharacteristicValueDAOObject() {
    }

    public CharacteristicValueDAOObject(long characteristicId, long productId, String characteristicValue) {
        this.characteristicId = characteristicId;
        this.productId = productId;
        this.characteristicValue = characteristicValue;
    }

    public long getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(long characteristicId) {
        this.characteristicId = characteristicId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getCharacteristicValue() {
        return characteristicValue;
    }

    public void setCharacteristicValue(String characteristicValue) {
        this.characteristicValue = characteristicValue;
    }
}
