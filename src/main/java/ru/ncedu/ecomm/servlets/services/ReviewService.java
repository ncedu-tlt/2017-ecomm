package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Review;
import ru.ncedu.ecomm.data.models.User;
import ru.ncedu.ecomm.servlets.models.ReviewViewModel;
import ru.ncedu.ecomm.servlets.models.builders.ReviewViewModelBuilder;

import java.util.ArrayList;
import java.util.List;

public class ReviewService {

    private ReviewService() {
    }

    private static ReviewService instance;

    public static synchronized ReviewService getInstance() {
        if (instance == null) {
            instance = new ReviewService();
        }
        return instance;
    }

    public List<ReviewViewModel> getReview(long productId) {
        ReviewViewModel userReview;

        List<ReviewViewModel> reviewsViewModel = new ArrayList<>();
        List<Review> reviews = getReviewByDAO(productId);

        for (Review review : reviews) {
            userReview = new ReviewViewModelBuilder()
                    .setUserId(review.getUserId())
                    .setUserName(getUserNameByUserId(review.getUserId()))
                    .setUserAvatarLink(getUserAvatarLinkByUserId(review.getUserId()))
                    .setRating(review.getRating())
                    .setReviewDate(review.getCreationDate())
                    .setDescription(review.getDescription())
                    .build();

            reviewsViewModel.add(userReview);
        }

        return reviewsViewModel;
    }

    private String getUserAvatarLinkByUserId(long userId) {
        User user = getUserById(userId);

        return getUserAvatarLink(user);
    }

    private String getUserAvatarLink(User user) {

        return user.getUserAvatar();
    }

    private String getUserNameByUserId(long userId) {
        User user = getUserById(userId);

        return getUserName(user);
    }

    private User getUserById(long userId) {
        return DAOFactory
                .getDAOFactory()
                .getUserDAO()
                .getUserById(userId);
    }

    private String getUserName(User user) {
        String userName;

        if (user.getFirstName().isEmpty() && user.getLastName().isEmpty()) {
            userName = user.getEmail();

        } else if (user.getFirstName().isEmpty()) {
            userName = user.getLastName();

        } else if (user.getLastName().isEmpty()) {
            userName = user.getFirstName();

        } else {
            userName = user.getFirstName() + " " + user.getLastName();
        }
        return userName;
    }

    private List<Review> getReviewByDAO(long productId) {
        return DAOFactory
                .getDAOFactory()
                .getReviewDAO()
                .getReviewsByProductId(productId);
    }
}
