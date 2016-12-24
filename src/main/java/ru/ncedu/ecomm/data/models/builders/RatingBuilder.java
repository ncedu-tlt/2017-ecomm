package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.Rating;

public class RatingBuilder {
    private long productId;
    private int rating;

    public RatingBuilder() {
    }

    public RatingBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public RatingBuilder setRating(int rating) {
        this.rating = rating;

        return this;
    }

    public Rating build(){
        return new Rating(
                productId,
                rating
        );
    }
}
