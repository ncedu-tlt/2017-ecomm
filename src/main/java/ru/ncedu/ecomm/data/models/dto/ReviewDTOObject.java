package ru.ncedu.ecomm.data.models.dto;

import java.sql.Date;

public class ReviewDTOObject {
    private long userId;
    private String description;
    private Date creationDate;
    private int rating;
    private String productName;
    private long productId;

    public ReviewDTOObject() {
    }

    public ReviewDTOObject(long userId, String description, Date creationDate, int rating, String productName, long productId) {
        this.userId = userId;
        this.description = description;
        this.creationDate = creationDate;
        this.rating = rating;
        this.productName = productName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
