package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.Review;

import java.sql.Date;


public class ReviewBuilder {
    private long productId;
    private long userId;
    private String description;
    private Date creationDate;
    private int rating;

    public ReviewBuilder() {
    }

    public ReviewBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public ReviewBuilder setUserId(long userId) {
        this.userId = userId;

        return this;
    }

    public ReviewBuilder setDescription(String description) {
        this.description = description;

        return this;
    }

    public ReviewBuilder setCreationDate(Date creationDate) {
        this.creationDate = creationDate;

        return this;
    }

    public ReviewBuilder setRating(int rating) {
        this.rating = rating;

        return this;
    }

    public Review build(){
        return new Review(
                productId,
                userId,
                description,
                creationDate,
                rating
        );
    }
}
