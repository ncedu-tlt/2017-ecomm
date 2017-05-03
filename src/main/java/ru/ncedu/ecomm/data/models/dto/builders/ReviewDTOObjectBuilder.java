package ru.ncedu.ecomm.data.models.dto.builders;

import ru.ncedu.ecomm.data.models.dto.ReviewDTOObject;

import java.sql.Date;

public class ReviewDTOObjectBuilder {

    private long userId;
    private String description;
    private Date creationDate;
    private int rating;
    private String product;

    public ReviewDTOObjectBuilder() {
    }

    public ReviewDTOObjectBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public ReviewDTOObjectBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ReviewDTOObjectBuilder setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ReviewDTOObjectBuilder setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public ReviewDTOObjectBuilder setProduct(String product) {
        this.product = product;
        return this;
    }

    public ReviewDTOObject build(){
        return new ReviewDTOObject(userId,
                description,
                creationDate,
                rating,
                product);
    }
}
