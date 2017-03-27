package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.CategoryDAOObject;
import ru.ncedu.ecomm.data.models.ProductDAOObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "BreadcrumbsServlet", urlPatterns = {"/breadcrumbs"})
public class BreadcrumbsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDAOObject> categories;

        if (!request.getParameter("product_id").isEmpty()) { //TODO: camelCase, имя параметра в константу
            long productId = Long.parseLong(request.getParameter("product_id"));
            if (productId != 0) {
                ProductDAOObject product = getDAOFactory().getProductDAO().getProductById(productId);
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

