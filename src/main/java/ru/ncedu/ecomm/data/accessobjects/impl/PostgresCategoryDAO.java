package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.CategoryDAO;
import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.data.models.builders.CategoryBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.utils.DBUtils.closeConnection;
import static ru.ncedu.ecomm.utils.DBUtils.closeStatement;

public class PostgresCategoryDAO implements CategoryDAO {

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT category_id, parent_id, name, description" +
                    " FROM public.categories");
            while (resultSet.next()) {
                Category category = new CategoryBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();

                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category addCategory(Category category) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.categories (parent_id, name, description)" +
                     " VALUES (?, ?, ?) " +
                     " RETURNING category_id")) {
            statement.setLong(1, category.getParentId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                category.setCategoryId(statement.getResultSet().getLong("category_id"));
            }
            return category;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category updateCategory(Category category) {

        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE public.categories SET parent_id = ?, name = ?," +
                            " description = ? WHERE category_id = ?")) {

            statement.setLong(1, category.getParentId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.setLong(4, category.getCategoryId());
            statement.execute();

            return category;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCategory(Category category) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM public.categories" +
                     " WHERE category_id = ?")) {

            statement.setLong(1, category.getCategoryId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category getCategoryById(long id) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT category_id, parent_id, name, description" +
                     " FROM public.categories" +
                     " WHERE category_id = ?")) {

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                return new CategoryBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Category> getCategoriesByParentId(long parentId) {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT category_id, parent_id, name, description" +
                     " FROM public.categories WHERE parent_id = ?")) {

            statement.setLong(1, parentId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new CategoryBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();

                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public List<Category> getCategoriesByHierarchy(long categoryId) {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("WITH RECURSIVE recquery (category_id, parent_id, name, description) as " +
                     "(SELECT category_id, parent_id, name, description from categories WHERE category_id = ? " +
                     "union " +
                     "SELECT categories.category_id ,categories.parent_id, categories.name, categories.description " +
                     "FROM categories INNER JOIN recquery ON (recquery.parent_id = categories.category_id)) " +
                     "SELECT category_id, parent_id, name, description FROM recquery ORDER BY category_id")) {


            statement.setLong(1, categoryId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Category category = new CategoryBuilder()
                        .setCategoryId(resultSet.getLong("category_id"))
                        .setParentId(resultSet.getLong("parent_id"))
                        .setName(resultSet.getString("name"))
                        .setDescription(resultSet.getString("description"))
                        .build();
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
}
