package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.data.models.Raiting;

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
        itemView(req, resp);
    }

    private void itemView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Category> categoriesForView = getCategoryByParametr(request);
        List<Product> products = getDAOFactory().getProductDAO().getProducts();
        List<Raiting> raitings = getDAOFactory().getReviewDAO().getAverageRaitingByProduct();

        request.setAttribute("raitingByProduct", raitings);
        request.setAttribute("categoriesForView", categoriesForView);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/views/pages/category.jsp").forward(request, response);
    }

    private List<Category> getCategoryByParametr(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("category_id"));

        List<Category> categories = getDAOFactory().getCategoryDAO().getCategoriesByParentId(id);
        if (categories.size() == 0) {
            categories = getDAOFactory().getCategoryDAO().getCategoriesByHierarchy(id);
        }
        return categories;
    }
}