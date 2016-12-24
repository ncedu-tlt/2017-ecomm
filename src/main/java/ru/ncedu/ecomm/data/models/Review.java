package ru.ncedu.ecomm.data.models;


import java.sql.Date;

public class Review {
    private long productId;
    private long userId;
    private String description;
    private Date creationDate;
    private int rating;

    public Review(){

    }

    public Review(long productId,
                  long userId,
                  String description,
                  Date creationDate,
                  int rating) {
        this.productId = productId;
        this.userId = userId;
        this.description = description;
        this.creationDate = creationDate;
        this.rating = rating;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
