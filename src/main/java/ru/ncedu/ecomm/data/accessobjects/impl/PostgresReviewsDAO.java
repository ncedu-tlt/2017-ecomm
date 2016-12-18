package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.ReviewsDAO;
import ru.ncedu.ecomm.data.models.Raiting;
import ru.ncedu.ecomm.data.models.Review;
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
                    "SELECT product_id, user_id, description, " +
                            "creation_date, raiting FROM public.reviews");

            while (resultSet.next()) {
                Review review = new Review();
                review.setProductId(resultSet.getLong("product_id"));
                review.setUserId(resultSet.getLong("user_id"));
                review.setCreationDate(resultSet.getDate("creation_date"));
                review.setDescription(resultSet.getString("description"));
                review.setReaiting(resultSet.getInt("raiting"));

                reviews.add(review);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }

    @Override
    public boolean addReviews(Review review) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.reviews (product_id, user_id, description,creation_date, raiting)" +
                             "VALUES (?,?,?,?,?)")) {
            statement.setLong(1, review.getProductId());
            statement.setLong(2, review.getUserId());
            statement.setString(3, review.getDescription());
            statement.setDate(4, (Date) review.getCreationDate());
            statement.setInt(5, review.getReaiting());

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Review updateReviews() {
        return null;
    }

    @Override
    public void deleteReviews() {

    }

    @Override
    public List<Review> getRewiesById(long productId) {
        return null;
    }

    @Override
    public List<Raiting> getAverageRaitingByProduct() {
        List<Raiting> raitings = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT product_id, avg(raiting) as average_raiting FROM reviews GROUP BY product_id;");
            while (resultSet.next()) {
                Raiting raiting = new Raiting(resultSet.getLong("product_id"),
                        resultSet.getInt("average_raiting"));
                raitings.add(raiting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raitings;
    }
}
