package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.ProductDAO;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.data.models.builders.ProductBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresProductDAO implements ProductDAO {

    private static final int MAX_ITEM_FOR_MAIN_PAGE = 6;
    private static final int MAX_ITEM_FOR_CATEGORY_PAGE = 6;

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();


        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  product_id,\n" +
                            "  category_id,\n" +
                            "  name,\n" +
                            "  description,\n" +
                            "  discount_id,\n" +
                            "  price\n" +
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
                     "INSERT INTO public.products\n" +
                             "(category_id,\n" +
                             " name,\n" +
                             " description,\n" +
                             " discount_id,\n" +
                             " price)\n" +
                             "VALUES (?, ?, ?, ?, ?)\n" +
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
                     "UPDATE public.products\n" +
                             "SET category_id = ?,\n" +
                             "  name          = ?,\n" +
                             "  description   = ?,\n" +
                             "  discount_id   = ?,\n" +
                             "  price         = ?\n" +
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
                     "DELETE FROM public.products\n" +
                             "WHERE product_id = ?")) {

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
                     "SELECT\n" +
                             "  product_id,\n" +
                             "  category_id,\n" +
                             "  name,\n" +
                             "  description,\n" +
                             "  discount_id,\n" +
                             "  price\n" +
                             "FROM public.products\n" +
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
                     "SELECT\n" +
                             "  product_id,\n" +
                             "  category_id,\n" +
                             "  name,\n" +
                             "  description,\n" +
                             "  discount_id,\n" +
                             "  price\n" +
                             "FROM public.products\n" +
                             "WHERE category_id = ?\n" +
                             "ORDER BY product_id ASC\n" +
                             "LIMIT " + MAX_ITEM_FOR_CATEGORY_PAGE)) {

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
    public List<Product> getProductAllChildrenCategory(long categoryId) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(

                             "WITH RECURSIVE req AS (\n" +
                             "  SELECT\n" +
                             "    category_id,\n" +
                             "    parent_id\n" +
                             "  FROM categories\n" +
                             "  WHERE category_id = ?\n" +
                             "  UNION\n" +
                             "  SELECT\n" +
                             "    categories.category_id,\n" +
                             "    categories.parent_id\n" +
                             "  FROM categories\n" +
                             "    JOIN req\n" +
                             "      ON categories.parent_id = req.category_id)\n" +
                             "SELECT\n" +
                             "  req.category_id,\n" +
                             "  req.parent_id,\n" +
                             "  product_id,\n" +
                             "  products.category_id,\n" +
                             "  products.name,\n" +
                             "  price,\n" +
                             "  description,\n" +
                             "  products.discount_id,\n" +
                             "  count(product_id) AS amount_product_in_category\n" +
                             "FROM req\n" +
                             "  JOIN products ON req.category_id = products.category_id\n" +
                             "GROUP BY req.category_id, req.parent_id, product_id\n" +
                             "ORDER BY req.category_id ASC,\n" +
                             "  req.parent_id ASC\n " +
                             "LIMIT " + MAX_ITEM_FOR_MAIN_PAGE)) {

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
                     "SELECT\n" +
                             "  product_id,\n" +
                             "  category_id,\n" +
                             "  products.name,\n" +
                             "  price,\n" +
                             "  description,\n" +
                             "  products.discount_id\n" +
                             "FROM public.products\n" +
                             "  JOIN discount\n" +
                             "  ON products.discount_id = discount.discount_id\n" +
                             "WHERE discount.value > 0\n" +
                             "ORDER BY discount.value DESC\n" +
                             "LIMIT " + MAX_ITEM_FOR_MAIN_PAGE)) {

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

    public List<Product> searchProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT *\n" +
                             "FROM public.products\n" +
                             "WHERE name ~* '" + name + "'")) {

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


