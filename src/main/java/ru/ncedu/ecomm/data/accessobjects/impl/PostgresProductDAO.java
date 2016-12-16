package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.ProductDAO;
import ru.ncedu.ecomm.data.models.Product;
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

        Statement statement = null;
        Connection connection = null;

        try {
            connection = DBUtils.getConnection();

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT product_id, category_id, name, description, discount_id, price" +
                    " FROM public.products");
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getLong("category_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getLong("discount_id"),
                        resultSet.getLong("price"));

                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return products;
    }

    @Override
    public Product addProduct(Product product) {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = DBUtils.getConnection();

            statement = connection.prepareStatement("INSERT INTO public.products (category_id, name, description, discount_id, price)" +
                    " VALUES (?, ?, ?, ?, ?) " +
                    " RETURNING product_id, category_id, name, description, discount_id, price");
            statement.setLong(1, product.getProductId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setLong(1, product.getDiscoundId());
            statement.setLong(1, product.getPrice());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                Product newProduct = new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getLong("category_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getLong("discount_id"),
                        resultSet.getLong("price"));
                return newProduct;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = DBUtils.getConnection();

            statement = connection.prepareStatement("UPDATE public.products" +
                    " SET category_id = ?, name = ?, description = ?, discount_id = ?, price = ?" +
                    " WHERE product_id = ?");
            statement.setLong(1, product.getCategoryId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setLong(4, product.getDiscoundId());
            statement.setLong(5, product.getPrice());
            statement.setLong(6, product.getProductId());
            statement.execute();

            statement = connection.prepareStatement("SELECT product_id, category_id, name, description, discount_id, price" +
                    " FROM public.products WHERE product_id = ?");
            statement.setLong(1, product.getProductId());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product updatedProduct = new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getLong("category_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getLong("discount_id"),
                        resultSet.getLong("price"));
                return updatedProduct;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return null;
    }

    @Override
    public void deleteProduct(Product product) {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = DBUtils.getConnection();

            statement = connection.prepareStatement("DELETE FROM public.products" +
                    " WHERE product_id = ?");
            statement.setLong(1, product.getProductId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    @Override
    public Product getProductById(long id) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();

            statement = connection.prepareStatement("SELECT product_id, category_id, name, description, discount_id, price" +
                    " FROM public.products WHERE product_id = ?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getLong("category_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getLong("discount_id"),
                        resultSet.getLong("price"));
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return null;
    }

    @Override
    public List<Product> getProductsByCategoryId(long categoryId) {
        List<Product> products = new ArrayList<>();

        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();

            statement = connection.prepareStatement("SELECT product_id, category_id, name, description, discount_id, price" +
                    " FROM public.products WHERE category_id = ?");
            statement.setLong(1, categoryId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong("product_id"),
                        resultSet.getLong("category_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getLong("discount_id"),
                        resultSet.getLong("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return null;
    }


}


