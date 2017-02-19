package ru.ncedu.ecomm.servlets.models;

import java.util.Date;

public class ReviewViewModel {
    private String userName;
    private long userId;
    private String userAvatarLink;
    private long productId;

    private int rating;
    private Date reviewDate;
    private String description;

    public ReviewViewModel(){}

    public ReviewViewModel(String userName,
                           long userId,
                           String userAvatarLink,
                           int rating,
                           Date reviewDate,
                           String description,
                           long productId) {
        this.userName = userName;
        this.userId = userId;
        this.userAvatarLink = userAvatarLink;
        this.rating = rating;
        this.reviewDate = reviewDate;
        this.description = description;
        this.productId = productId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserAvatarLink() {
        return userAvatarLink;
    }

    public void setUserAvatarLink(String userAvatarLink) {
        this.userAvatarLink = userAvatarLink;
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

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

