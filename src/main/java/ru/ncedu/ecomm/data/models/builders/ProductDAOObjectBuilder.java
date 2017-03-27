package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.ProductDAOObject;

public class ProductDAOObjectBuilder {
    private long productId;
    private long categoryId;
    private String name;
    private String description;
    private long discountId;
    private long price;

    public ProductDAOObjectBuilder() {

    }

    public ProductDAOObjectBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public ProductDAOObjectBuilder setCategoryId(long categoryId) {
        this.categoryId = categoryId;

        return this;
    }

    public ProductDAOObjectBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public ProductDAOObjectBuilder setDescription(String description) {
        this.description = description;

        return this;
    }

    public ProductDAOObjectBuilder setDiscountId(long discountId) {
        this.discountId = discountId;

        return this;
    }

    public ProductDAOObjectBuilder setPrice(long price) {
        this.price = price;

        return this;
    }

    public ProductDAOObject build() {
        return new ProductDAOObject(
                productId,
                categoryId,
                name,
                description,
                discountId,
                price
        );
    }
}
