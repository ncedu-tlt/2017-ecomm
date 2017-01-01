package ru.ncedu.ecomm.servlets.viewmodel.viewmodelbuilders;

import ru.ncedu.ecomm.servlets.viewmodel.ProductItemsView;

public class ProductItemsViewBuilder {
    private long categoryId;
    private long productId;
    private int rating;
    private int discount;
    private long price;
    private String name;
    private String imageUrl;

    public ProductItemsViewBuilder() {
    }

    public ProductItemsViewBuilder setCategoryId(long categoryId) {
        this.categoryId = categoryId;

        return this;
    }

    public ProductItemsViewBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public ProductItemsViewBuilder setRating(int rating) {
        this.rating = rating;

        return this;
    }

    public ProductItemsViewBuilder setDiscount(int discount) {
        this.discount = discount;

        return this;
    }

    public ProductItemsViewBuilder setPrice(long price) {
        this.price = price;

        return this;
    }

    public ProductItemsViewBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public ProductItemsViewBuilder setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;

        return this;
    }

    public ProductItemsView build(){
        return new ProductItemsView(
                categoryId,
                productId,
                rating,
                discount,
                price,
                name,
                imageUrl
        );
    }
}
