package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.ProductDAO;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.data.models.builders.ProductBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.utils.DBUtils.closeConnection;
import static ru.ncedu.ecomm.utils.DBUtils.closeStatement;

public class PostgresProductDAO implements ProductDAO {

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();


        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT product_id, " +
                            "category_id, " +
                            "name, " +
                            "description, " +
                            "discount_id, " +
                            "price " +
                            "FROM public.products");

            while (resultSet.next()) {
                Product product = new ProductBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();

                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public Product addProduct(Product product) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.products " +
                             "(category_id, " +
                             "name, " +
                             "description, " +
                             "discount_id, " +
                             "price) " +
                             "VALUES (?, ?, ?, ?, ?) " +
                             "RETURNING product_id")) {

            statement.setLong(1, product.getProductId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setLong(4, product.getDiscountId());
            statement.setLong(5, product.getPrice());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Product updateProduct(Product product) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.products " +
                             "SET category_id = ?, " +
                             "name = ?, " +
                             "description = ?, " +
                             "discount_id = ?, " +
                             "price = ? " +
                             "WHERE product_id = ?")) {

            statement.setLong(1, product.getCategoryId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setLong(4, product.getDiscountId());
            statement.setLong(5, product.getPrice());
            statement.setLong(6, product.getProductId());
            statement.execute();

            return product;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(Product product) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.products" +
                             " WHERE product_id = ?")) {

            statement.setLong(1, product.getProductId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(long id) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT product_id, " +
                             "category_id, " +
                             "name, " +
                             "description, " +
                             "discount_id, " +
                             "price " +
                             "FROM public.products " +
                             "WHERE product_id = ?")) {


            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ProductBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Product> getProductsByCategoryId(long categoryId) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT product_id, " +
                             "category_id, " +
                             "name, " +
                             "description, " +
                             "discount_id, " +
                             "price " +
                             "FROM public.products " +
                             "WHERE category_id = ? " +
                             "ORDER BY product_id ASC " +
                             "LIMIT 6")) {

            statement.setLong(1, categoryId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new ProductBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<Product> getBestOffersProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT " +
                             "product_id, " +
                             "category_id, " +
                             "name, " +
                             "price, " +
                             "description, " +
                             "discount_id " +
                             "FROM public.products " +
                             "WHERE discount_id > 1" +
                             "ORDER BY discount_id DESC " +
                             "LIMIT 12")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new ProductBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();

                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}


