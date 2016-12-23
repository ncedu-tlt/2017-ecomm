package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.data.models.CharacteristicValue;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.data.models.Raiting;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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


        List<Product> products = getDAOFactory().getProductDAO().getProducts();
        List<Category> categoriesForView = categoriesFilter(getCategoryByParametr(request), products);
        List<Raiting> raitings = getDAOFactory().getReviewDAO().getAverageRaitingByProduct();
        List<CharacteristicValue> characteristicValues = getDAOFactory().getCharacteristicValueDAO().getCharacteristicValue();



        request.setAttribute("characteristicValues", characteristicValues);
        request.setAttribute("raitingByProduct", raitings);
        request.setAttribute("categoriesForView", categoriesForView);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/views/pages/category.jsp").forward(request, response);
    }

    private List<Category> categoriesFilter(List<Category> categoriesForView, List<Product> products) {
        HashSet<Category> categoriesSet = new HashSet<>();
        List<Category> filteringCategory = new ArrayList<>();


        for (Category category : categoriesForView) {
            if (category.getParentId() == 0){
                filteringCategory.add(category);
            }
            for (Product product : products) {
                if (product.getCategoryId() == category.getCategoryId()) {
                    categoriesSet.add(category);
                }
            }
        }

        filteringCategory.addAll(categoriesSet);

        Collections.sort(filteringCategory, new Comparator<Category>() {
            public int compare(Category categoryOne, Category categoryTwo) {
                if (categoryOne.getCategoryId() > categoryTwo.getCategoryId()) {
                    return 1;
                } else if (categoryOne.getCategoryId() == categoryTwo.getCategoryId()){
                    return 0;
                } else {
                    return -1;
                }
            }
        });


        return filteringCategory;
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