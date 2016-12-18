package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.Raiting;
import ru.ncedu.ecomm.data.models.Review;

import java.util.List;

public interface ReviewsDAO {
    List<Review> getReviews();

    boolean addReviews(Review review);

    Review updateReviews();

    void deleteReviews();

    List<Review> getRewiesById(long productId);

    List<Raiting> getAverageRaitingByProduct();
}
