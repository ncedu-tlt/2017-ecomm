package ru.ncedu.ecomm.data.models;

public class CharacteristicValue {
    private long characteristicId;
    private long productId;
    private String cahracteristicValue;

    public CharacteristicValue() {
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

    public String getCahracteristicValue() {
        return cahracteristicValue;
    }

    public void setCahracteristicValue(String cahracteristicValue) {
        this.cahracteristicValue = cahracteristicValue;
    }
}
