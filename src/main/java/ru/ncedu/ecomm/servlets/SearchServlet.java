package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.dao.ProductDAOObject;
import ru.ncedu.ecomm.servlets.models.CategoryViewModel;
import ru.ncedu.ecomm.servlets.models.builders.CategoryViewBuilder;
import ru.ncedu.ecomm.servlets.services.ProductViewService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("search");

        List<ProductDAOObject> products = DAOFactory.getDAOFactory().getProductDAO().searchProductsByName(query);
        List<CategoryViewModel> categoryViewModels = new ArrayList<>();

        categoryViewModels.add(new CategoryViewBuilder()
                .setId(null)
                .setName("Search")
                .setProducts(ProductViewService.getInstance().getProductsToView(products))
                .build());
        request.setAttribute("categoriesForView", categoryViewModels);
        request.getRequestDispatcher("/views/pages/index.jsp").forward(request, response);
    }
}
