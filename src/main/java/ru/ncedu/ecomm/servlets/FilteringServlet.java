package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.Configuration;
import ru.ncedu.ecomm.data.models.ProductDAOObject;
import ru.ncedu.ecomm.servlets.models.*;
import ru.ncedu.ecomm.servlets.models.builders.CategoryViewBuilder;
import ru.ncedu.ecomm.servlets.models.builders.PriceRangeViewModelBuilder;
import ru.ncedu.ecomm.servlets.services.FilterService;
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

        Long categoryId = getCategoryId(request.getParameter("category_id"));
        if(categoryId != null) {
            PriceRangeViewModel priceRange = getPriceRange(request);

            List<FilterViewModel> filters = FilterService.getInstance()
                    .getFiltersValuesChecked(categoryId, request.getParameterMap());
            List<ProductViewModel> products = getProducts(filters, priceRange, categoryId);
            List<CategoryViewModel> categoryViewModels = new ArrayList<>();

            categoryViewModels.add(new CategoryViewBuilder()
                    .setId(null)
                    .setName("Filter")
                    .setProducts(products)
                    .build());

            request.setAttribute("priceRange", getPriceRange(request));
            request.setAttribute("filters", filters);
            request.setAttribute("categoriesForView", categoryViewModels);
            request.getRequestDispatcher(Configuration.getProperty("page.category")).forward(request, response);
        }
        else{
            request.getRequestDispatcher("/home").forward(request, response);
        }
    }

     private Long getCategoryId(String categoryIdByRequest) {
        if (categoryIdByRequest != null && !categoryIdByRequest.isEmpty()) {
            return Long.parseLong(categoryIdByRequest);
        } else {
            return null;
        }
    }

    private Double getPrice(String priceByRequest){
        if(priceByRequest != null && !priceByRequest.isEmpty())
            return Double.parseDouble(priceByRequest);
        else
            return null;
    }

    private PriceRangeViewModel getPriceRange(HttpServletRequest request) {

        PriceRangeViewModelBuilder priceRange = new PriceRangeViewModelBuilder();
        long categoryId = getCategoryId(request.getParameter("category_id"));

        Double min = getPrice(request.getParameter("min"));
        Double max = getPrice(request.getParameter("max"));

        if (min != null) {
            priceRange.setMin(min);
        } else
            priceRange.setMin(getDAOFactory().getProductDAO()
                    .getProductsPriceRangeByCategoryId(categoryId).getMin());

        if (max != null) {
            priceRange.setMax(max);
        } else
            priceRange.setMax(getDAOFactory().getProductDAO()
                    .getProductsPriceRangeByCategoryId(categoryId).getMax());

        return priceRange.build();
    }

    private List<ProductViewModel> getProducts(List<FilterViewModel> filters, PriceRangeViewModel priceRange, long categoryId) {

        List<ProductDAOObject> products = getDAOFactory()
                .getProductDAO().getFilteredProducts(filters, priceRange,categoryId);
        return ProductViewService.getInstance().getProductsToView(products);
    }


}
