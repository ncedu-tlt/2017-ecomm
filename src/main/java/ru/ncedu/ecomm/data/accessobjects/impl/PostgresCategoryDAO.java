package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.CategoryDAO;
import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.utils.DBUtils.closeConnection;
import static ru.ncedu.ecomm.utils.DBUtils.closeStatement;

/**
 * Created by Antoine on 07.12.2016.
 */
public class PostgresCategoryDAO implements CategoryDAO {

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = DBUtils.getConnection();

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select category_id, parent_id, name, description" +
                    "from public.categories");
            while (resultSet.next()) {
                Category category = new Category(resultSet.getLong("category_id"), resultSet.getLong("parent_id"),
                                                resultSet.getString("name"), resultSet.getString("description"));

                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return categories;
    }

    @Override
    public Category addCategory(Category category) {
        PreparedStatement statement = null;
        Connection connection = null;
        Category newCategory = null;

        try {
            connection = DBUtils.getConnection();

            statement = connection.prepareStatement("INSERT INTO public.categories (parent_id, name, description)" +
                                                    " VALUES (?, ?, ?)");
            statement.setLong(1, category.getParentId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.execute();

            ResultSet resultSet = statement.executeQuery("SELECT category_id, parent_id, name, description" +
                    " FROM public.categories" +
                    " WHERE category_id =" +
                    " (SELECT MAX(category_id) from public.categories)");

            newCategory = new Category(resultSet.getLong("category_id"), resultSet.getLong("parent_id"),
                                        resultSet.getString("name"), resultSet.getString("description"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return newCategory;
    }

    @Override
    public Category updateCategory(Category category) {
        PreparedStatement statement = null;
        Connection connection = null;
        Category updatedCategory = null;

        try {
            connection = DBUtils.getConnection();

            statement = connection.prepareStatement("UPDATE public.categories" +
                    " SET parent_id = ?, name = ?, description = ?" +
                    " WHERE category_id = ?");
            statement.setLong(1, category.getParentId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.setLong(4, category.getCategoryId());
            statement.execute();

            String sql = "SELECT category_id, parent_id, name, description" +
                    " FROM public.categories" +
                    " WHERE category_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, category.getCategoryId());

            ResultSet resultSet = statement.executeQuery(sql);
            updatedCategory = new Category(resultSet.getLong("category_id"), resultSet.getLong("parent_id"),
                    resultSet.getString("name"), resultSet.getString("description"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return updatedCategory;
    }

    @Override
    public void deleteCategory(Category category) {
        PreparedStatement statement = null;
        Connection connection = null;

        try {
            connection = DBUtils.getConnection();

            statement = connection.prepareStatement("DELETE FROM public.categories" +
                    " WHERE category_id = ?");
            statement.setLong(1, category.getCategoryId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }
}
