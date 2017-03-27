package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.CategoryDAOObject;
import ru.ncedu.ecomm.servlets.models.CategoryViewModel;
import ru.ncedu.ecomm.servlets.models.FilterViewModel;
import ru.ncedu.ecomm.servlets.models.PriceRangeViewModel;
import ru.ncedu.ecomm.servlets.services.FilterService;
import ru.ncedu.ecomm.servlets.services.ProductViewService;

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

    static final String PARAMETER_NAME_FOR_CATEGORY_ID = "category_id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        browseCategories(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        browseCategories(req, resp);
    }

    private void browseCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<CategoryViewModel> categoryViewModels = ProductViewService
                .getInstance()
                .getCategoriesById(getCategoryListByRequest(request));

        long categoryId = getCategoryId(request.getParameter(PARAMETER_NAME_FOR_CATEGORY_ID));

        if(categoryId != 0) {
            PriceRangeViewModel priceRange = getDAOFactory()
                    .getProductDAO()
                    .getProductsPriceRangeByCategoryId(categoryId);

            List<FilterViewModel> filters = FilterService.getInstance()
                    .getFilters(categoryId);

            request.setAttribute("priceRange", priceRange);
            request.setAttribute("filters", filters);
        }

        request.setAttribute("categoriesForView", categoryViewModels);
        request.getRequestDispatcher("/views/pages/category.jsp").forward(request, response);
    }

    private List<CategoryDAOObject> getCategoryListByRequest(HttpServletRequest request) {
        List<CategoryDAOObject> categories;
        String categoryIdByRequest = request.getParameter(PARAMETER_NAME_FOR_CATEGORY_ID);

        if (categoryIdByRequest == null || getCategoryId(categoryIdByRequest) == 0) {
            categories = getParentCategory();
        } else {
            categories = getCategoriesById(
                    getCategoryId(categoryIdByRequest)
            );
        }
        return categories;
    }

    private List<CategoryDAOObject> getCategoriesById(long categoryId) {
        return getDAOFactory()
                .getCategoryDAO()
                .getAllNotEmptyChildrenCategoriesById(categoryId);
    }

    private List<CategoryDAOObject> getParentCategory() {
        return getDAOFactory()
                .getCategoryDAO()
                .getParentCategories();
    }

    private long getCategoryId(String categoryIdByRequest) {
        if (categoryIdByRequest != null) {
            return Long.parseLong(categoryIdByRequest);
        } else {
            return 0;
        }
    }
}