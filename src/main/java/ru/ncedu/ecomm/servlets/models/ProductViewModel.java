package ru.ncedu.ecomm.servlets.models;

public class ProductViewModel {
    private long id;
    private int rating;
    private long discount;
    private long price;
    private String name;
    private String imageUrl;

    public ProductViewModel() {
    }

    public ProductViewModel(long productId,
                            int rating,
                            long discount,
                            long price,
                            String name,
                            String imageUrl) {

        this.id = productId;
        this.rating = rating;
        this.discount = discount;
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long productId) {
        this.id = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
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
