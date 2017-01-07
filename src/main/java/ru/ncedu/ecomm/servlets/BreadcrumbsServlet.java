package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;
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
public class BreadcrumbsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories;

        if (!request.getParameter("product_id").isEmpty()) {
            long productId = Long.parseLong(request.getParameter("product_id"));
            if (productId != 0) {
                Product product = getDAOFactory().getProductDAO().getProductById(productId);
                request.setAttribute("product", product);
                if(product != null) {
                    categories = getDAOFactory().getCategoryDAO().getCategoriesByHierarchy(product.getCategoryId());
                    request.setAttribute("categories", categories);
                }
            }
        } else {
            if (!request.getParameter("category_id").isEmpty()) {
                long categoryId = Long.parseLong(request.getParameter("category_id"));
                if (categoryId != 0) {
                    categories = getDAOFactory().getCategoryDAO().getCategoriesByHierarchy(categoryId);
                    request.setAttribute("categories", categories);
                }
            }
        }
    }

}

