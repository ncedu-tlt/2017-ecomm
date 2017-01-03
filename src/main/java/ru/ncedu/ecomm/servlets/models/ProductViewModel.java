package ru.ncedu.ecomm.servlets.models;

public class ProductViewModel {
    private long categoryId;
    private long productId;
    private int rating;
    private int discount;
    private long price;
    private String name;
    private String imageUrl;

    public ProductViewModel(){

    }

    public ProductViewModel(long categoryId,
                            long productId,
                            int rating,
                            int discount,
                            long price,
                            String name,
                            String imageUrl) {

        this.categoryId = categoryId;
        this.productId = productId;
        this.rating = rating;
        this.discount = discount;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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
