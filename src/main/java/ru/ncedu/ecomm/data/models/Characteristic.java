package ru.ncedu.ecomm.data.models;

public class Characteristic {
    private long characteristicId;
    private long categoryId;
    private String characteristicName;
    private long getCharacteristicGroupId;

    public Characteristic() {
    }

    public long getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(long characteristicId) {
        this.characteristicId = characteristicId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

    public long getGetCharacteristicGroupId() {
        return getCharacteristicGroupId;
    }

    public void setGetCharacteristicGroupId(long getCharacteristicGroupId) {
        this.getCharacteristicGroupId = getCharacteristicGroupId;
    }
}
