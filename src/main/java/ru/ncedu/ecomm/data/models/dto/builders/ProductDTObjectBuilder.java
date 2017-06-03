package ru.ncedu.ecomm.data.models.dto.builders;

import ru.ncedu.ecomm.data.models.dto.ProductDTObject;
import ru.ncedu.ecomm.servlets.models.CharacteristicGroupModel;

import java.util.List;

public class ProductDTObjectBuilder {
    private long productId;
    private long categoryId;
    private String name;
    private String description;
    private long price;
    private long discountId;
    private List<CharacteristicGroupModel> characteristicGroups;

    public ProductDTObjectBuilder() {

    }

    public ProductDTObjectBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public ProductDTObjectBuilder setCategoryId(long categoryId) {
        this.categoryId = categoryId;

        return this;
    }

    public ProductDTObjectBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public ProductDTObjectBuilder setDescription(String description) {
        this.description = description;

        return this;
    }

    public ProductDTObjectBuilder setPrice(long price) {
        this.price = price;

        return this;
    }

    public ProductDTObjectBuilder setDiscountId(long discountId) {
        this.discountId = discountId;

        return this;
    }

    public ProductDTObjectBuilder setCharacteristicGroups(List<CharacteristicGroupModel> characteristicGroups) {
        this.characteristicGroups = characteristicGroups;

        return this;
    }

    public ProductDTObject build() {
        return new ProductDTObject(
                productId,
                categoryId,
                name,
                description,
                price,
                discountId,
                characteristicGroups
        );
    }
}