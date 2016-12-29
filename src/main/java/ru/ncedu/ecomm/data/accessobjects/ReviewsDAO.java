package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Rating;
import ru.ncedu.ecomm.data.models.Review;

import java.util.List;

public interface ReviewsDAO {
    List<Review> getReviews();

    Review addReviews(Review review);

    Review updateReviews(Review review);

    void deleteReviews(Review review);

    List<Review> getReviewsByProductId(long productId);

    List<Review> getReviewsByUserId(long userId);

    List<Rating> getAverageRatingByProduct();

    Rating getAverageRatingByProductId(long productId);
}
