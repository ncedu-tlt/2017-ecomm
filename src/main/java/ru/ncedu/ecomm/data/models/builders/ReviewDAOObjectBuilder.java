package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.ReviewDAOObject;

import java.sql.Date;


public class ReviewDAOObjectBuilder {
    private long productId;
    private long userId;
    private String description;
    private Date creationDate;
    private int rating;

    public ReviewDAOObjectBuilder() {
    }

    public ReviewDAOObjectBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public ReviewDAOObjectBuilder setUserId(long userId) {
        this.userId = userId;

        return this;
    }

    public ReviewDAOObjectBuilder setDescription(String description) {
        this.description = description;

        return this;
    }

    public ReviewDAOObjectBuilder setCreationDate(Date creationDate) {
        this.creationDate = creationDate;

        return this;
    }

    public ReviewDAOObjectBuilder setRating(int rating) {
        this.rating = rating;

        return this;
    }

    public ReviewDAOObject build(){
        return new ReviewDAOObject(
                productId,
                userId,
                description,
                creationDate,
                rating
        );
    }
}
