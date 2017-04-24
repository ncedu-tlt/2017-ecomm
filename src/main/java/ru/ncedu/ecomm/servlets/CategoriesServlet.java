package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.dao.CategoryDAOObject;
import ru.ncedu.ecomm.servlets.models.CategoryHierarchyViewModel;
import ru.ncedu.ecomm.servlets.models.builders.CategoryHierarchyViewBuilder;

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

    private List<CategoryHierarchyViewModel> getSubcategoriesViewByParentID(long id){
        List<CategoryDAOObject> categories = getDAOFactory().getCategoryDAO().getCategoriesByParentId(id);
        List<CategoryHierarchyViewModel> subcategories = new ArrayList<>();
        for (CategoryDAOObject category : categories) {
            subcategories.add(new CategoryHierarchyViewBuilder()
                    .setId(category.getCategoryId())
                    .setName(category.getName())
                    .setSubcategories(new ArrayList<>())
                    .build());
        }
        return subcategories;
    }

    private void process(HttpServletRequest request) {
        List<CategoryDAOObject> categories = getDAOFactory().getCategoryDAO().getCategories();
        List<CategoryHierarchyViewModel> parentCategories = new ArrayList<>();

        for (CategoryDAOObject category : categories) {
            if (category.getParentId() == 0) {
                List<CategoryHierarchyViewModel> subcategories = getSubcategoriesViewByParentID(category.getCategoryId());

                parentCategories.add(new CategoryHierarchyViewBuilder()
                        .setId(category.getCategoryId())
                        .setName(category.getName())
                        .setSubcategories(subcategories)
                        .build());

                for (CategoryHierarchyViewModel subcategory : subcategories) {
                    subcategory.getSubcategories().addAll(getSubcategoriesViewByParentID(subcategory.getId()));
                }
            }
        }

        request.setAttribute("parentCategories", parentCategories);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request);
    }
}
