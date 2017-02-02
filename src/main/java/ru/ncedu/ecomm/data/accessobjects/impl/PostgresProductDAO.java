package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.ProductDAO;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.data.models.builders.ProductBuilder;
import ru.ncedu.ecomm.data.models.PriceRangeModel;
import ru.ncedu.ecomm.data.models.builders.PriceRangeModelBuilder;
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

    @Override
    public List<Product> getProductsFromPriceRangeByCategoryId(PriceRangeModel priceRange, long categoryId) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT product_id, category_id, name, description, discount_id, price FROM products\n" +
                             " WHERE category_id IN (\n" +
                             "  WITH RECURSIVE recquery\n" +
                             "(category_id, parent_id) AS\n" +
                             "(SELECT category_id, parent_id\n" +
                             "FROM categories\n" +
                             "WHERE category_id = ?\n" +
                             "UNION\n" +
                             "SELECT\n" +
                             "categories.category_id,\n" +
                             "categories.parent_id\n" +
                             "FROM categories\n" +
                             "INNER JOIN recquery\n" +
                             "ON (recquery.category_id = categories.parent_id))\n" +
                             "SELECT category_id\n" +
                             "FROM recquery) AND (price - (price * (SELECT value FROM discount WHERE discount_id = products.discount_id) / 100 )) BETWEEN ? AND ?")) {

            statement.setLong(1, categoryId);
            statement.setDouble(2, priceRange.getMin());
            statement.setDouble(3, priceRange.getMax());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(new ProductBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public PriceRangeModel getProductsPriceRangeByCategoryId(long categoryId) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT min(price) AS min, max(price) AS max FROM " +
                             "(SELECT product_id, category_id, name, description, discount_id,\n" +
                             "(price - (price * (SELECT value FROM discount " +
                             "WHERE discount_id = products.discount_id) / 100 )) AS price FROM products) AS products\n" +
                             "WHERE category_id IN(SELECT id FROM (WITH RECURSIVE categoriesRec ( id,parent_id) AS (\n" +
                             "SELECT category_id,parent_id\n" +
                             "FROM categories WHERE category_id = (SELECT * FROM(WITH RECURSIVE rec (category_id, parent_id) AS\n" +
                             "(SELECT category_id, parent_id FROM categories\n" +
                             "  WHERE category_id = ?\n" +
                             " UNION\n" +
                             " SELECT ct.category_id, ct.parent_id\n" +
                             " FROM categories ct INNER JOIN rec\n" +
                             "     ON (rec.parent_id = ct.category_id))\n" +
                             "SELECT category_id FROM rec ORDER BY category_id) AS category_id LIMIT 1)\n" +
                             "UNION\n" +
                             "SELECT T.category_id, T.parent_id\n" +
                             "FROM categories T INNER JOIN categoriesRec ON(categoriesRec.id= T.parent_id))\n" +
                             "SELECT * FROM categoriesRec) AS id)")) {

            statement.setLong(1, categoryId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new PriceRangeModelBuilder()
                        .setMin(resultSet.getDouble("min"))
                        .setMax(resultSet.getDouble("max"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}


