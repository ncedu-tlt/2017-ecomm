package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.data.models.Product;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    private final static long HIDDEN_ID = -1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("search");

        List<Product> products = DAOFactory.getDAOFactory().getProductDAO().searchProductsByName(query);
        List<CategoryViewModel> categoryViewModels = new ArrayList<>();

        categoryViewModels.add(new CategoryViewBuilder()
                .setName(products.isEmpty() ? "Sorry, no products matched \"" + query + "\"" : "Search results")
                .setId(HIDDEN_ID)
                .setProducts(ProductViewService.getInstance().getProductsToView(products))
                .build());
        request.setAttribute("categoriesForView", categoryViewModels);
        request.getRequestDispatcher("/views/pages/index.jsp").forward(request, response);
    }

}
