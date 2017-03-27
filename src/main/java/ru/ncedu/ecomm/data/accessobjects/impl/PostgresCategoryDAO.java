package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.CategoryDAO;
import ru.ncedu.ecomm.data.models.CategoryDAOObject;
import ru.ncedu.ecomm.data.models.builders.CategoryDAOObjectBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class PostgresCategoryDAO implements CategoryDAO {
    private static final Logger LOG = Logger.getLogger(PostgresCategoryDAO.class);

    @Override
    public List<CategoryDAOObject> getCategories() {
        List<CategoryDAOObject> categories = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  category_id,\n" +
                            "  parent_id,\n" +
                            "  name,\n" +
                            "  description\n" +
                            "FROM public.categories\n" +
                            "ORDER BY category_id ASC"
            );
            while (resultSet.next()) {
                CategoryDAOObject category = new CategoryDAOObjectBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();

                categories.add(category);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public List<CategoryDAOObject> getAllNotEmptyCategories() {
        List<CategoryDAOObject> categories = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "WITH RECURSIVE req AS (\n" +
                            "  SELECT\n" +
                            "    category_id,\n" +
                            "    parent_id,\n" +
                            "    name,\n" +
                            "    description\n" +
                            "  FROM categories\n" +
                            "  UNION\n" +
                            "  SELECT\n" +
                            "    categories.category_id,\n" +
                            "    categories.parent_id,\n" +
                            "    categories.name,\n" +
                            "    categories.description\n" +
                            "  FROM categories\n" +
                            "    JOIN req\n" +
                            "      ON categories.parent_id = req.category_id)\n" +
                            "SELECT\n" +
                            "  req.category_id,\n" +
                            "  req.parent_id,\n" +
                            "  req.description,\n" +
                            "  req.name,\n" +
                            "  count(product_id) AS amount_product_in_category\n" +
                            "FROM req\n" +
                            "  JOIN products ON req.category_id = products.category_id\n" +
                            "GROUP BY req.category_id, req.parent_id, req.description, req.name\n" +
                            "ORDER BY req.category_id ASC,\n" +
                            "  req.parent_id ASC"
            );
            while (resultSet.next()) {
                CategoryDAOObject category = new CategoryDAOObjectBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();

                categories.add(category);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public CategoryDAOObject addCategory(CategoryDAOObject category) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.categories\n" +
                             "(parent_id,\n" +
                             " name,\n" +
                             " description)\n" +
                             "VALUES (?, ?, ?)\n" +
                             "RETURNING category_id"
             )) {
            statement.setLong(1, category.getParentId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                category.setCategoryId(statement.getResultSet().getLong("category_id"));
            }

            LOG.info(null);
            return category;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public CategoryDAOObject updateCategory(CategoryDAOObject category) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.categories\n" +
                             "SET parent_id = ?,\n" +
                             "  name        = ?,\n" +
                             "  description = ?\n" +
                             "WHERE category_id = ?"
             )) {

            statement.setLong(1, category.getParentId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.setLong(4, category.getCategoryId());
            statement.execute();

            LOG.info(null);
            return category;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCategory(CategoryDAOObject category) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.categories\n" +
                             "WHERE category_id = ?"
             )) {

            statement.setLong(1, category.getCategoryId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public CategoryDAOObject getCategoryById(long id) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  category_id,\n" +
                             "  parent_id,\n" +
                             "  name,\n" +
                             "  description\n" +
                             "FROM public.categories\n" +
                             "WHERE category_id = ?"
             )) {

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                LOG.info(null);
                return new CategoryDAOObjectBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<CategoryDAOObject> getParentCategories() {
        List<CategoryDAOObject> categories = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  category_id,\n" +
                            "  parent_id,\n" +
                            "  name,\n" +
                            "  description\n" +
                            "FROM public.categories\n" +
                            "WHERE parent_id ISNULL\n" +
                            "ORDER BY category_id ASC"
            );
            while (resultSet.next()) {
                CategoryDAOObject category = new CategoryDAOObjectBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();

                categories.add(category);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return categories;
    }


    @Override
    public List<CategoryDAOObject> getAllNotEmptyChildrenCategoriesById(long categoryId) {
        List<CategoryDAOObject> categories = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "WITH RECURSIVE req AS (\n" +
                             "  SELECT\n" +
                             "    category_id,\n" +
                             "    parent_id,\n" +
                             "    name,\n" +
                             "    description\n" +
                             "  FROM categories\n" +
                             "  WHERE category_id = ?\n" +
                             "  UNION\n" +
                             "  SELECT\n" +
                             "    categories.category_id,\n" +
                             "    categories.parent_id,\n" +
                             "    categories.name,\n" +
                             "    categories.description\n" +
                             "  FROM categories\n" +
                             "    JOIN req\n" +
                             "      ON categories.parent_id = req.category_id)\n" +
                             "SELECT\n" +
                             "  req.category_id,\n" +
                             "  req.parent_id,\n" +
                             "  req.description,\n" +
                             "  req.name,\n" +
                             "  count(product_id) AS amount_product_in_category\n" +
                             "FROM req\n" +
                             "  JOIN products ON req.category_id = products.category_id\n" +
                             "GROUP BY req.category_id, req.parent_id, req.description, req.name\n" +
                             "ORDER BY req.category_id ASC,\n" +
                             "  req.parent_id ASC"
             )) {

            statement.setLong(1, categoryId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CategoryDAOObject category = new CategoryDAOObjectBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();

                categories.add(category);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public List<CategoryDAOObject> getCategoriesByParentId(long parentId) {
        List<CategoryDAOObject> categories = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  category_id,\n" +
                             "  parent_id,\n" +
                             "  name,\n" +
                             "  description\n" +
                             "FROM public.categories\n" +
                             "WHERE parent_id = ?\n" +
                             "ORDER BY category_id"
             )) {

            statement.setLong(1, parentId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CategoryDAOObject category = new CategoryDAOObjectBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();

                categories.add(category);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public List<CategoryDAOObject> getCategoriesByHierarchy(long categoryId) {
        List<CategoryDAOObject> categories = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "WITH RECURSIVE recquery\n" +
                             "(category_id,\n" +
                             "    parent_id,\n" +
                             "    name,\n" +
                             "    description) AS\n" +
                             "(SELECT\n" +
                             "   category_id,\n" +
                             "   parent_id,\n" +
                             "   name,\n" +
                             "   description\n" +
                             " FROM categories\n" +
                             " WHERE category_id = ?\n" +
                             " UNION\n" +
                             " SELECT\n" +
                             "   categories.category_id,\n" +
                             "   categories.parent_id,\n" +
                             "   categories.name,\n" +
                             "   categories.description\n" +
                             " FROM categories\n" +
                             "   INNER JOIN recquery\n" +
                             "     ON (recquery.parent_id = categories.category_id))\n" +
                             "SELECT\n" +
                             "  category_id,\n" +
                             "  parent_id,\n" +
                             "  name,\n" +
                             "  description\n" +
                             "FROM recquery\n" +
                             "ORDER BY category_id"
             )) {


            statement.setLong(1, categoryId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                CategoryDAOObject category = new CategoryDAOObjectBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();
                categories.add(category);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return categories;
    }
}
