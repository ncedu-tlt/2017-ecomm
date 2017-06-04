package ru.ncedu.ecomm.data.models.dto;

import java.util.List;

public class ProductDTObject {
    private long productId;
    private long categoryId;
    private String name;
    private String description;
    private long price;
    private long discountId;
    private List<CharacteristicGroupDTObj> characteristicGroups;

    public ProductDTObject() {
    }

    public ProductDTObject(long productId,
                           long categoryId,
                           String name,
                           String description,
                           long price,
                           long discountId,
                           List<CharacteristicGroupDTObj> characteristicGroups) {

        this.productId = productId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discountId = discountId;
        this.characteristicGroups = characteristicGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public List<CharacteristicGroupDTObj> getCharacteristicGroups() {
        return characteristicGroups;
    }

    public void setCharacteristicGroups(List<CharacteristicGroupDTObj> characteristicGroups) {
        this.characteristicGroups = characteristicGroups;
    }
}

