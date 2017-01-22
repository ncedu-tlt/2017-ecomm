package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.ReviewViewModel;

import java.util.Date;

public class ReviewViewModelBuilder {
    private String userName;
    private long userId;
    private String userAvatarLink;

    private int rating;
    private Date reviewDate;
    private String description;

    public ReviewViewModelBuilder() {
    }

    public ReviewViewModelBuilder setUserName(String userName) {
        this.userName = userName;

        return this;
    }

    public ReviewViewModelBuilder setUserId(long userId) {
        this.userId = userId;

        return this;
    }

    public ReviewViewModelBuilder setUserAvatarLink(String userAvatarLink) {
        this.userAvatarLink = userAvatarLink;

        return this;
    }

    public ReviewViewModelBuilder setRating(int rating) {
        this.rating = rating;

        return this;
    }

    public ReviewViewModelBuilder setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;

        return this;
    }

    public ReviewViewModelBuilder setDescription(String description) {
        this.description = description;

        return this;
    }

    public ReviewViewModel build(){
        return new ReviewViewModel(
                userName,
                userId,
                userAvatarLink,
                rating,
                reviewDate,
                description
        );
    }
}
