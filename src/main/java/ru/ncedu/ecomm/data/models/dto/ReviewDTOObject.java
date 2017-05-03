package ru.ncedu.ecomm.data.models.dto;

import java.sql.Date;

public class ReviewDTOObject {
    private long userId;
    private String description;
    private Date creationDate;
    private int rating;
    private String product;

    public ReviewDTOObject() {
    }

    public ReviewDTOObject(long userId, String description, Date creationDate, int rating, String product) {
        this.userId = userId;
        this.description = description;
        this.creationDate = creationDate;
        this.rating = rating;
        this.product = product;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
