package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.ProductDAO;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.utils.DBUtils.closeConnection;
import static ru.ncedu.ecomm.utils.DBUtils.closeStatement;

public class PostgresProductDAO implements ProductDAO {

    // TODO: 15.12.2016 дописать PostgresProductDAO   
    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = DBUtils.getConnection();

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT product_id, product_id, name, description, discount_id, price" +
                                                         " FROM public.products");
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong("product_id"), resultSet.getLong("category_id"),
                                                resultSet.getString("name"), resultSet.getString("description"),
                                                resultSet.getLong("discount_id"), resultSet.getLong("price"));

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
                Product product = new Product(resultSet.getLong("product_id"), resultSet.getLong("category_id"),
                        resultSet.getString("name"), resultSet.getString("description"),
                        resultSet.getLong("discount_id"), resultSet.getLong("price"));
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return null;
    }

}
