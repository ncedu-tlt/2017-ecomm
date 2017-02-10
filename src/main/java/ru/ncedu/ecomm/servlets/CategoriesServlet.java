package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.servlets.models.CategoriesViewModel;
import ru.ncedu.ecomm.servlets.models.builders.CategoriesViewBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "CategoriesServlet", urlPatterns = {"/categories"})
public class CategoriesServlet extends HttpServlet {

    private List<CategoriesViewModel> getSubcategoriesViewByParentID(long id){
        List<Category> categories = getDAOFactory().getCategoryDAO().getCategoriesByParentId(id);
        List<CategoriesViewModel> subcategories = new ArrayList<>();
        for (Category category : categories) {
            subcategories.add(new CategoriesViewBuilder()
                    .setId(category.getCategoryId())
                    .setName(category.getName())
                    .setSubcategories(new ArrayList<>())
                    .build());
        }
        return subcategories;
    }

    private void process(HttpServletRequest request) {
        List<Category> categories = getDAOFactory().getCategoryDAO().getCategories();
        List<CategoriesViewModel> heads = new ArrayList<>();

        for (Category category : categories) {
            if (category.getParentId() == 0) {
                List<CategoriesViewModel> subcategories = getSubcategoriesViewByParentID(category.getCategoryId());

                heads.add(new CategoriesViewBuilder()
                        .setId(category.getCategoryId())
                        .setName(category.getName())
                        .setSubcategories(subcategories)
                        .build());

                for (CategoriesViewModel subcategory : subcategories) {
                    subcategory.getSubcategories().addAll(getSubcategoriesViewByParentID(subcategory.getId()));
                }
            }
        }

        request.setAttribute("heads", heads); //TODO: а почему heads?
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request);
    }
}
