package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.ReviewsDAO;
import ru.ncedu.ecomm.data.models.Rating;
import ru.ncedu.ecomm.data.models.Review;
import ru.ncedu.ecomm.data.models.builders.RatingBuilder;
import ru.ncedu.ecomm.data.models.builders.ReviewBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresReviewsDAO implements ReviewsDAO {

    @Override
    public List<Review> getReviews() {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  product_id,\n" +
                            "  user_id,\n" +
                            "  description,\n" +
                            "  creation_date,\n" +
                            "  raiting\n" +
                            "FROM public.reviews");

            while (resultSet.next()) {
                Review review = new ReviewBuilder()
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setDescription(resultSet.getString("description"))
                        .setProductId(resultSet.getLong("product_id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setRating(resultSet.getInt("raiting"))
                        .build();

                reviews.add(review);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    @Override
    public Review addReviews(Review review) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.reviews\n" +
                             "(product_id,\n" +
                             " user_id,\n" +
                             " description,\n" +
                             " creation_date,\n" +
                             " raiting)\n" +
                             "VALUES (?, ?, ?, ?, ?)\n" +
                             "RETURNING product_id")) {

            statement.setLong(1, review.getProductId());
            statement.setLong(2, review.getUserId());
            statement.setString(3, review.getDescription());
            statement.setDate(4, review.getCreationDate());
            statement.setInt(5, review.getRating());

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                review.setProductId(resultSet.getLong(1));
            }

            return review;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Review updateReviews(Review review) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.reviews\n" +
                             "SET description = ?,\n" +
                             "  creation_date = ?,\n" +
                             "  raiting       = ?\n" +
                             "WHERE product_id = ?\n" +
                             "      AND user_id = ?"
             )) {
            statement.setString(1, review.getDescription());
            statement.setDate(2, review.getCreationDate());
            statement.setInt(3, review.getRating());
            statement.setLong(4, review.getProductId());
            statement.setLong(5, review.getUserId());
            statement.execute();

            return review;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Review userReviewByUserIdAndProductId(long productId, long userId) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  product_id\n" +
                             "FROM public.reviews\n" +
                             "WHERE product_id = ?\n" +
                             "AND user_id = ?"
             )) {

            statement.setLong(1, productId);
            statement.setLong(2, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new ReviewBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteReviews(Review review) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.reviews\n" +
                             "WHERE product_id = ?\n" +
                             "      AND user_id = ?"
             )) {
            statement.setLong(1, review.getProductId());
            statement.setLong(2, review.getUserId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Review> getReviewsByProductId(long productId) {
        List<Review> reviews = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  description,\n" +
                             "  creation_date,\n" +
                             "  raiting,\n" +
                             "  user_id\n" +
                             "FROM public.reviews\n" +
                             "WHERE product_id = ?"
             )) {
            statement.setLong(1, productId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Review review = new ReviewBuilder()
                        .setDescription(resultSet.getString("description"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setRating(resultSet.getInt("raiting"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setProductId(productId)
                        .build();

                reviews.add(review);
            }
            return reviews;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Review> getReviewsByUserId(long userId) {
        List<Review> reviews = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  description,\n" +
                             "  creation_date,\n" +
                             "  raiting,\n" +
                             "  product_id\n" +
                             "FROM public.reviews\n" +
                             "WHERE user_id = ?"
             )) {
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Review review = new ReviewBuilder()
                        .setDescription(resultSet.getString("description"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setRating(resultSet.getInt("raiting"))
                        .setProductId(resultSet.getLong("product_id"))
                        .setProductId(userId)
                        .build();

                reviews.add(review);
            }
            return reviews;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Rating> getAverageRatingByProduct() {
        List<Rating> raitings = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  product_id,\n" +
                            "  avg(raiting) AS average_rating\n" +
                            "FROM reviews\n" +
                            "GROUP BY product_id"
            );

            while (resultSet.next()) {
                Rating raiting = new RatingBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setRating(resultSet.getInt("average_rating"))
                        .build();

                raitings.add(raiting);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return raitings;
    }

    @Override
    public Rating getAverageRatingByProductId(long productId) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  product_id,\n" +
                             "  avg(raiting) AS average_rating\n" +
                             "FROM reviews\n" +
                             "WHERE product_id = ?\n" +
                             "GROUP BY product_id"
             )) {

            statement.setLong(1, productId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new RatingBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setRating(resultSet.getInt("average_rating"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
