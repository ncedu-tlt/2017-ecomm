package ru.ncedu.ecomm.data.models;

public class ProductItemsView {
    private long categoryId;
    private long productId;
    private int rating;
    private long discountId;
    private long price;
    private String name;
    private String imageUrl;

    public ProductItemsView(long categoryId,
                            long productId,
                            int rating,
                            long discountId,
                            long price,
                            String name,
                            String imageUrl) {

        this.categoryId = categoryId;
        this.productId = productId;
        this.rating = rating;
        this.discountId = discountId;
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
