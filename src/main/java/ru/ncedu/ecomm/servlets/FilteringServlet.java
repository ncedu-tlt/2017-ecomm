package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.PriceRangeModel;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.data.models.builders.PriceRangeModelBuilder;
import ru.ncedu.ecomm.servlets.models.CategoryViewModel;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;
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

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "FilteringServlet", urlPatterns = {"/filtering"})
public class FilteringServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long categoryId = getCategoryParentId(request);

        List<Product> products = getDAOFactory().getProductDAO().getProductsFromPriceRangeByCategoryId(getPriceRange(request), categoryId);
        List<ProductViewModel> productsView = ProductViewService.getInstance().getProductsToView(products);
        List<CategoryViewModel> categoryViewModels = new ArrayList<>();

        categoryViewModels.add(new CategoryViewBuilder()
                .setId(categoryId)
                .setName("Filtered")
                .setProducts(productsView)
                .build());
        request.setAttribute("categoriesForView", categoryViewModels);
        request.getRequestDispatcher("/views/pages/category.jsp").forward(request, response);

    }

    private long getCategoryParentId(HttpServletRequest request) {
        long categoryId = Long.parseLong(request.getParameter("category_id"));
        return getDAOFactory().getCategoryDAO().getCategoriesByHierarchy(categoryId).get(0).getCategoryId();
    }

    private PriceRangeModel getPriceRange(HttpServletRequest request) {
        PriceRangeModelBuilder priceRange = new PriceRangeModelBuilder();
        long categoryId = getCategoryParentId(request);

        if (!request.getParameter("min").isEmpty()) {
            priceRange.setMin(Long.parseLong(request.getParameter("min")));
        } else
            priceRange.setMin(getDAOFactory().getProductDAO().getProductsPriceRangeByCategoryId(categoryId).getMin());

        if (!request.getParameter("max").isEmpty()) {
            priceRange.setMax(Long.parseLong(request.getParameter("max")));
        } else
            priceRange.setMax(getDAOFactory().getProductDAO().getProductsPriceRangeByCategoryId(categoryId).getMax());

        return priceRange.build();
    }


}
