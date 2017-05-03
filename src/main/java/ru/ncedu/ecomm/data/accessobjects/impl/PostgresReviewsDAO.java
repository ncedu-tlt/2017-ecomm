package ru.ncedu.ecomm.data.accessobjects.impl;

import org.apache.log4j.Logger;
import ru.ncedu.ecomm.data.accessobjects.ReviewsDAO;
import ru.ncedu.ecomm.data.models.dao.Rating;
import ru.ncedu.ecomm.data.models.dao.ReviewDAOObject;
import ru.ncedu.ecomm.data.models.dao.builders.RatingBuilder;
import ru.ncedu.ecomm.data.models.dao.builders.ReviewDAOObjectBuilder;
import ru.ncedu.ecomm.data.models.dto.ReviewDTOObject;
import ru.ncedu.ecomm.data.models.dto.builders.ReviewDTOObjectBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresReviewsDAO implements ReviewsDAO {
    private static final Logger LOG  = Logger.getLogger(PostgresReviewsDAO.class);

    @Override
    public List<ReviewDAOObject> getReviews() {
        List<ReviewDAOObject> reviews = new ArrayList<>();
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
                ReviewDAOObject review = new ReviewDAOObjectBuilder()
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setDescription(resultSet.getString("description"))
                        .setProductId(resultSet.getLong("product_id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setRating(resultSet.getInt("raiting"))
                        .build();

                reviews.add(review);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return reviews;
    }

    @Override
    public ReviewDAOObject addReviews(ReviewDAOObject review) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.reviews\n" +
                             "(product_id,\n" +
                             " user_id,\n" +
                             " description,\n" +
                             " creation_date,\n" +
                             " raiting)\n" +
                             "VALUES (?, ?, ?, ?, ?)\n")) {

            statement.setLong(1, review.getProductId());
            statement.setLong(2, review.getUserId());
            statement.setString(3, review.getDescription());
            statement.setDate(4, review.getCreationDate());
            statement.setInt(5, review.getRating());
            statement.execute();

            LOG.info(null);
            return review;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ReviewDAOObject updateReviews(ReviewDAOObject review) {

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

            LOG.info(null);
            return review;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ReviewDAOObject userReviewByUserIdAndProductId(long productId, long userId) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT \n" +
                             " product_id,\n" +
                             " user_id,\n" +
                             " description,\n" +
                             " creation_date,\n" +
                             " raiting\n" +
                             "FROM public.reviews\n" +
                             "WHERE product_id = ?\n" +
                             "AND user_id = ?"
             )) {

            statement.setLong(1, productId);
            statement.setLong(2, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                LOG.info(null);
                return new ReviewDAOObjectBuilder()
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setDescription(resultSet.getString("description"))
                        .setProductId(resultSet.getLong("product_id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setRating(resultSet.getInt("raiting"))
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteReviews(ReviewDAOObject review) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.reviews\n" +
                             "WHERE product_id = ?\n" +
                             "      AND user_id = ?"
             )) {
            statement.setLong(1, review.getProductId());
            statement.setLong(2, review.getUserId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<ReviewDAOObject> getReviewsByProductId(long productId) {
        List<ReviewDAOObject> reviews = new ArrayList<>();

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
                ReviewDAOObject review = new ReviewDAOObjectBuilder()
                        .setDescription(resultSet.getString("description"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setRating(resultSet.getInt("raiting"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setProductId(productId)
                        .build();

                reviews.add(review);
            }

            LOG.info(null);
            return reviews;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ReviewDAOObject> getReviewsByUserId(long userId) {
        List<ReviewDAOObject> reviews = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  description,\n" +
                             "  creation_date,\n" +
                             "  raiting,\n" +
                             "  product_id\n" +
                             "FROM reviews\n" +
                             "WHERE user_id = ?"
             )) {
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ReviewDAOObject review = new ReviewDAOObjectBuilder()
                        .setDescription(resultSet.getString("description"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setRating(resultSet.getInt("raiting"))
                        .setProductId(resultSet.getLong("product_id"))
                        .setUserId(userId)
                        .build();

                reviews.add(review);
            }
            LOG.info(null);
            return reviews;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
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

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
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

                LOG.info(null);
                return new RatingBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setRating(resultSet.getInt("average_rating"))
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<ReviewDTOObject> getReviewsByUserIdForManagement(long userId) {
        List<ReviewDTOObject> reviews = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  rw.description,\n" +
                             "  rw.creation_date,\n" +
                             "  rw.raiting,\n" +
                             "  rw.product_id,\n" +
                             "  pd.name\n" +
                             "FROM reviews rw, products pd\n" +
                             "WHERE rw.product_id = pd.product_id\n" +
                             "AND rw.user_id = ?"
             )) {
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ReviewDTOObject review = new ReviewDTOObjectBuilder()
                        .setDescription(resultSet.getString("description"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setRating(resultSet.getInt("raiting"))
                        .setProduct(resultSet.getString("name"))
                        .setUserId(userId)
                        .build();
                reviews.add(review);
            }
            LOG.info(null);
            return reviews;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
