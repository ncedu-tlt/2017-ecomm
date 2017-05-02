package ru.ncedu.ecomm.data.models.dto.builders;

import ru.ncedu.ecomm.data.models.dto.ProductDTOObject;

public class ProductDTOObjectBuilder {
    private long productId;
    private String name;
    private String description;
    private long price;

    public ProductDTOObjectBuilder() {

    }

    public ProductDTOObjectBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public ProductDTOObjectBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public ProductDTOObjectBuilder setDescription(String description) {
        this.description = description;

        return this;
    }

    public ProductDTOObjectBuilder setPrice(long price) {
        this.price = price;

        return this;
    }

    public ProductDTOObject build() {
        return new ProductDTOObject(
                productId,
                name,
                description,
                price
        );
    }
}