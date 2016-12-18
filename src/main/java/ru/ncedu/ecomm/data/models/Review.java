package ru.ncedu.ecomm.data.models;


import java.util.Date;

public class Review {
    private long productId;
    private long userId;
    private String description;
    private Date creationDate;
    private int reaiting;

    public Review(){

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

    public int getReaiting() {
        return reaiting;
    }

    public void setReaiting(int reaiting) {
        this.reaiting = reaiting;
    }
}
