package ru.ncedu.ecomm.data.accessobjects.impl;

import org.apache.log4j.Logger;
import ru.ncedu.ecomm.data.accessobjects.ProductDAO;
import ru.ncedu.ecomm.data.models.dao.ProductDAOObject;
import ru.ncedu.ecomm.data.models.dao.builders.ProductDAOObjectBuilder;
import ru.ncedu.ecomm.servlets.models.*;
import ru.ncedu.ecomm.servlets.models.builders.CharacteristicGroupModelBuilder;
import ru.ncedu.ecomm.servlets.models.builders.PriceRangeViewModelBuilder;
import ru.ncedu.ecomm.servlets.models.builders.ProductDetailsModelBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostgresProductDAO implements ProductDAO {

    private static final int MAX_ITEM_FOR_MAIN_PAGE = 6;
    private static final int MAX_ITEM_FOR_CATEGORY_PAGE = 6;
    private static final Logger LOG  = Logger.getLogger(PostgresProductDAO.class);

    @Override
    public List<ProductDAOObject> getProducts() {
        List<ProductDAOObject> products = new ArrayList<>();


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
                ProductDAOObject product = new ProductDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();

                products.add(product);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return products;
    }

    @Override
    public ProductDAOObject addProduct(ProductDAOObject product) {

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

                LOG.info(null);
                return product;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ProductDAOObject updateProduct(ProductDAOObject product) {

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

            LOG.info(null);
            return product;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(ProductDAOObject product) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.products\n" +
                             "WHERE product_id = ?")) {

            statement.setLong(1, product.getProductId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductDAOObject getProductById(long id) {

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

                LOG.info(null);
                return new ProductDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<ProductDAOObject> getProductsByCategoryId(long categoryId) {
        List<ProductDAOObject> products = new ArrayList<>();

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
                ProductDAOObject product = new ProductDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();
                products.add(product);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<ProductDAOObject> getProductAllChildrenCategory(long categoryId) {
        List<ProductDAOObject> products = new ArrayList<>();
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
                ProductDAOObject product = new ProductDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();

                products.add(product);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<ProductDAOObject> getBestOffersProducts() {
        List<ProductDAOObject> products = new ArrayList<>();
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
                ProductDAOObject product = new ProductDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();

                products.add(product);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<ProductDAOObject> searchProductsByName(String name) {
        List<ProductDAOObject> products = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT *\n" +
                             "FROM public.products\n" +
                             "WHERE name ~*  ? ")) {

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductDAOObject product = new ProductDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();

                products.add(product);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

    public PriceRangeViewModel getProductsPriceRangeByCategoryId(long categoryId) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT min(price) AS min, max(price) AS max\n" +
                             "FROM (SELECT product_id, category_id, name, description, discount_id,\n" +
                             "        (price - (price * (SELECT value FROM discount\n" +
                             "        WHERE discount_id = products.discount_id) / 100)) AS price\n" +
                             "      FROM products) AS products\n" +
                             "WHERE category_id IN (SELECT id FROM (WITH RECURSIVE recCategoriesId (id, parent_id) AS\n" +
                             "  (SELECT category_id, parent_id\n" +
                             "   FROM categories\n" +
                             "   WHERE category_id = ?\n" +
                             "   UNION\n" +
                             "   SELECT CT.category_id, CT.parent_id\n" +
                             "   FROM categories CT INNER JOIN recCategoriesId ON (recCategoriesId.id = CT.parent_id))\n" +
                             "  SELECT *\n" +
                             "  FROM recCategoriesId) AS id)")) {

            statement.setLong(1, categoryId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                LOG.info(null);
                return new PriceRangeViewModelBuilder()
                        .setMin(resultSet.getDouble("min"))
                        .setMax(resultSet.getDouble("max"))
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ProductDetailsModel getFullProductById(long productId) {
        ProductDetailsModel product = null;
        List<CharacteristicGroupModel> characteristics = getCharacteristicsForProductById(productId);

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  (SELECT avg(r.raiting)\n" +
                             "   FROM reviews r\n" +
                             "   WHERE r.product_id = p.product_id)                                 AS rating,\n" +
                             "  p.category_id,\n" +
                             "  p.product_id,\n" +
                             "  p.price,\n" +
                             "  (p.price - (p.price * (SELECT d.value\n" +
                             "                         FROM discount d\n" +
                             "                         WHERE p.discount_id = d.discount_id)) / 100) AS discount,\n" +
                             "  p.name,\n" +
                             "  p.description\n" +
                             "\n" +
                             "FROM products p\n" +
                             "WHERE product_id = ?;")) {

            statement.setLong(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LOG.info(null);
                product = new ProductDetailsModelBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setRating(resultSet.getInt("rating"))
                        .setId(resultSet.getLong("product_id"))
                        .setPrice(resultSet.getLong("price"))
                        .setDiscount(resultSet.getLong("discount"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setCharacteristicGroupModels(characteristics)
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return product;
    }

    private List<CharacteristicGroupModel> getCharacteristicsForProductById(long productId) {
        List <CharacteristicGroupModel> characteristicsForProduct = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  cg.characteristic_group_id,\n" +
                             "  cg.name\n" +
                             "FROM characteristic_groups cg\n" +
                             "  LEFT JOIN characteristics c ON c.characteristic_group_id = cg.characteristic_group_id\n" +
                             "WHERE c.category_id =\n" +
                             "      (WITH RECURSIVE rec(category_id, parent_id)\n" +
                             "      AS\n" +
                             "      (\n" +
                             "        SELECT\n" +
                             "          category_id,\n" +
                             "          parent_id\n" +
                             "        FROM categories\n" +
                             "        WHERE categories.parent_id IS NULL\n" +
                             "        UNION ALL\n" +
                             "        SELECT\n" +
                             "          categories.category_id,\n" +
                             "          categories.parent_id\n" +
                             "        FROM categories, rec\n" +
                             "        WHERE categories.parent_id = rec.category_id\n" +
                             "      )\n" +
                             "      SELECT parent_id\n" +
                             "      FROM rec\n" +
                             "      WHERE category_id = (SELECT category_id\n" +
                             "                           FROM products\n" +
                             "                           WHERE product_id = ?))\n" +
                             "GROUP BY cg.characteristic_group_id;")) {

            statement.setLong(1, productId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CharacteristicGroupModel characteristicGroup = new CharacteristicGroupModelBuilder()
                        .setCharacteristicGroupName(resultSet.getString("name"))
                        .setCharacteristics(getCharacteristicsByGroupId(productId, resultSet.getLong("characteristic_group_id")))
                        .build();

                characteristicsForProduct.add(characteristicGroup);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return characteristicsForProduct;
    }

    private List<CharacteristicModel> getCharacteristicsByGroupId(long productId, long characteristic_group_id) {
        List <CharacteristicModel> characteristicsValueForProduct = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  c.name,\n" +
                             "  cv.value\n" +
                             "FROM characteristic_groups cg\n" +
                             "  LEFT JOIN characteristics c ON c.characteristic_group_id = cg.characteristic_group_id\n" +
                             "  LEFT JOIN characteristic_values cv ON c.characteristic_id = cv.characteristic_id\n" +
                             "WHERE cv.product_id = ?\n" +
                             "      AND c.characteristic_group_id = ?;")) {

            statement.setLong(1, productId);
            statement.setLong(2, characteristic_group_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                CharacteristicModel characteristicModel = new CharacteristicModel();
                characteristicModel.setName(resultSet.getString("name"));
                characteristicModel.setValue(resultSet.getString("value"));

                characteristicsValueForProduct.add(characteristicModel);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return characteristicsValueForProduct;
    }

    /**
     * Return filtered products by category, subcategories and price range
     */

    public List<ProductDAOObject> getFilteredProducts(List<FilterViewModel> filters, PriceRangeViewModel priceRange, long categoryId) {
        List<ProductDAOObject> products = new ArrayList<>();

        String query = "SELECT * FROM products\n" +
                "WHERE category_id IN (\n" +
                "SELECT id FROM (\n" +
                "WITH RECURSIVE recCategoriesId (id, parent_id) AS\n" +
                "(SELECT category_id, parent_id FROM categories\n" +
                "WHERE category_id = ?\n" +
                "UNION\n" +
                "SELECT CT.category_id, CT.parent_id\n" +
                "FROM categories CT INNER JOIN recCategoriesId ON (recCategoriesId.id = CT.parent_id))\n" +
                "SELECT * FROM recCategoriesId) AS id)\n" +
                "      AND price BETWEEN ? AND ?\n" +
                "AND product_id IN (SELECT t.product_id FROM characteristic_values t ";
        String subQuery = "";

        Map<Long, List<String>> filtersMap = new HashMap<>();

        if (!filters.isEmpty()) {
            for (FilterViewModel filter : filters) {
                if (filter.isValuesHaveChecked()) {
                    subQuery += "INTERSECT\n" +
                            " SELECT product_id FROM characteristic_values\n" +
                            "   WHERE characteristic_id = ? AND value IN (";
                    filtersMap.put(filter.getId(), new ArrayList<>());
                    for (FilterValueViewModel value : filter.getValues()) {
                        if (value.isChecked()) {
                            subQuery += "?,";
                            filtersMap.get(filter.getId()).add(value.getName());
                        }
                    }
                    subQuery = subQuery.substring(0, subQuery.length() - 1) + ") ";
                }
            }
        }
        query += subQuery + ") GROUP BY products.product_id";

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, categoryId);
            statement.setDouble(2, priceRange.getMin());
            statement.setDouble(3, priceRange.getMax());

            int index = 4;
            for (Map.Entry<Long, List<String>> entry : filtersMap.entrySet()) {
                statement.setLong(index++, entry.getKey());
                for (String value : entry.getValue()) {
                    statement.setString(index++, value);
                }
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(new ProductDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build());
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<ProductDAOObject> getProductByOrderId(long orderId) {
        List<ProductDAOObject> products = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  products.product_id,\n" +
                             "  products.product_id,\n" +
                             "  products.category_id,\n" +
                             "  products.name,\n" +
                             "  products.description,\n" +
                             "  products.discount_id,\n" +
                             "  products.price\n" +
                             "FROM sales_orders\n" +
                             "  JOIN order_items ON sales_orders.sales_order_id = order_items.sales_order_id\n" +
                             "  JOIN products ON order_items.product_id = products.product_id\n" +
                             "WHERE order_items.sales_order_id = ?")) {

            statement.setLong(1, orderId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductDAOObject product = new ProductDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .setDiscountId(resultSet.getLong("discount_id"))
                        .setPrice(resultSet.getLong("price"))
                        .build();

                products.add(product);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return products;
    }

}


