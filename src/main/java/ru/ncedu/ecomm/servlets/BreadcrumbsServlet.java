package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ru.ncedu.ecomm.utils.DBUtils.closeConnection;
import static ru.ncedu.ecomm.utils.DBUtils.closeStatement;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BreadcrumbsServlet", urlPatterns = {"/breadcrumbs"})
public class BreadcrumbsServlet extends HttpServlet{

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            long id = Long.parseLong(request.getParameter("category_id"));
            List<Category> categories = getCategoryHierarchy(id);

            request.setAttribute("categories", categories);
        }

    private List<Category> getCategoryHierarchy(long id) {
        List<Category> categories = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = DBUtils.getConnection();

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("WITH RECURSIVE recquery (category_id, parent_id, name) as\n" +
                    "(SELECT category_id, parent_id, name from categories\n" +
                    "     WHERE category_id = " + id +"     union\n" +
                    "     select categories.category_id ,categories.parent_id, categories.name\n" +
                    "          FROM categories INNER JOIN recquery ON (recquery.parent_id = categories.category_id))\n" +
                    "select category_id, name from recquery ORDER BY category_id;");
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getLong("category_id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return categories;
    }
}

