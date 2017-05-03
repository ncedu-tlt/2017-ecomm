package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.Rating;
import ru.ncedu.ecomm.data.models.dao.ReviewDAOObject;
import ru.ncedu.ecomm.data.models.dto.ReviewDTOObject;

import java.util.List;

public interface ReviewsDAO {
    List<ReviewDAOObject> getReviews();

    ReviewDAOObject addReviews(ReviewDAOObject review);

    ReviewDAOObject updateReviews(ReviewDAOObject review);

    ReviewDAOObject userReviewByUserIdAndProductId(long productId, long userId);

    void deleteReviews(ReviewDAOObject review);

    List<ReviewDAOObject> getReviewsByProductId(long productId);

    List<ReviewDAOObject> getReviewsByUserId(long userId);

    List<Rating> getAverageRatingByProduct();

    Rating getAverageRatingByProductId(long productId);

    List<ReviewDTOObject> getReviewsByUserIdForManagement(long userId);
}
