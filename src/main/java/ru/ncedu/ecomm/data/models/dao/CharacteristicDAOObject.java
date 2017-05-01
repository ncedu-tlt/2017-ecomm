package ru.ncedu.ecomm.data.models.dao;

public class CharacteristicDAOObject {
    private long characteristicId;
    private long categoryId;
    private String characteristicName;
    private long characteristicGroupId;
    private boolean filterable;

    public CharacteristicDAOObject(long characteristicId,
                                   long categoryId,
                                   String characteristicName,
                                   long characteristicGroupId,
                                   boolean filterable) {

        this.characteristicId = characteristicId;
        this.categoryId = categoryId;
        this.characteristicName = characteristicName;
        this.characteristicGroupId = characteristicGroupId;
        this.filterable = filterable;

    }

    public CharacteristicDAOObject() {

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

    public long getCharacteristicGroupId() {
        return characteristicGroupId;
    }

    public void setCharacteristicGroupId(long characteristicGroupId) {
        this.characteristicGroupId = characteristicGroupId;
    }

    public boolean isFilterable() { return filterable; }

    public void setFilterable(boolean filterable) { this.filterable = filterable; }
}
