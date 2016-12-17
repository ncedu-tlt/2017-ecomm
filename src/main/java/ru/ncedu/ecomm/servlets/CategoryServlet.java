package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.data.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        itemView(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // handle operations
        itemView(req, resp);
    }

    private void itemView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories;
        List<Product> products;
            categories = getDAOFactory().getCategoryDAO().getCategories();
            request.setAttribute("categories", categories);
            products = getDAOFactory().getProductDAO().getProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/views/pages/category.jsp").forward(request, response);

    }
}