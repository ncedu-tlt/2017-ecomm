package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.Product;

public class ProductBuilder {
    private long productId;
    private long categoryId;
    private String name;
    private String description;
    private long discountId;
    private long price;

    public ProductBuilder() {

    }

    public ProductBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public ProductBuilder setCategoryId(long categoryId) {
        this.categoryId = categoryId;

        return this;
    }

    public ProductBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.description = description;

        return this;
    }

    public ProductBuilder setDiscountId(long discountId) {
        this.discountId = discountId;

        return this;
    }

    public ProductBuilder setPrice(long price) {
        this.price = price;

        return this;
    }

    public Product build() {
        return new Product(
                productId,
                categoryId,
                name,
                description,
                discountId,
                price
        );
    }
}
