package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.dao.ReviewDAOObject;
import ru.ncedu.ecomm.data.models.dao.UserDAOObject;
import ru.ncedu.ecomm.servlets.models.ReviewViewModel;
import ru.ncedu.ecomm.servlets.models.builders.ReviewViewModelBuilder;

import java.util.LinkedList;
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

    public LinkedList<ReviewViewModel> getReview(long productId) {
        ReviewViewModel reviewViewModel;

        LinkedList<ReviewViewModel> reviewsViewModels = new LinkedList<>();
        List<ReviewDAOObject> reviews = getReviewByDAO(productId);

        for (ReviewDAOObject review : reviews) {

            if (review != null) {
                reviewViewModel = getReviewModel(review, productId);
                reviewsViewModels.add(reviewViewModel);
            }
        }

        return reviewsViewModels;
    }

    public ReviewViewModel getReviewModel(ReviewDAOObject review, long productId) {
        return new ReviewViewModelBuilder()
                .setUserId(review.getUserId())
                .setUserName(getUserNameByUserId(review.getUserId()))
                .setUserAvatarLink(getUserAvatarLinkByUserId(review.getUserId()))
                .setRating(review.getRating())
                .setReviewDate(review.getCreationDate())
                .setDescription(review.getDescription())
                .setProductId(productId)
                .build();
    }


    private String getUserAvatarLinkByUserId(long userId) {
        UserDAOObject user = getUserById(userId);

        return user != null ? user.getUserAvatar() : null;
    }

    private String getUserNameByUserId(long userId) {
        UserDAOObject user = getUserById(userId);

        return user != null ? UserService
                .getInstance()
                .getUserName(user) : null;
    }

    private UserDAOObject getUserById(long userId) {
        return DAOFactory
                .getDAOFactory()
                .getUserDAO()
                .getUserById(userId);
    }

    private List<ReviewDAOObject> getReviewByDAO(long productId) {
        return DAOFactory
                .getDAOFactory()
                .getReviewDAO()
                .getReviewsByProductId(productId);
    }
}
