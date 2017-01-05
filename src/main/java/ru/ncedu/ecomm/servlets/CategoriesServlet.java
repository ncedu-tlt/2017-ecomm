package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.servlets.models.CategoriesViewModel;
import ru.ncedu.ecomm.data.models.Category;
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

@WebServlet(name = "CategoriesServlet", urlPatterns = {"/CategoriesServlet"})
public class CategoriesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> categories = getDAOFactory().getCategoryDAO().getCategories();
        List<CategoriesViewModel> heads = new ArrayList<CategoriesViewModel>();
        List<CategoriesViewModel> child = new ArrayList<CategoriesViewModel>();


        for (Category category : categories) {
            if (category.getParentId() == 0) {
                List<Category> subcategories = getDAOFactory().getCategoryDAO().getCategoriesByParentId(category.getCategoryId());

                heads.add(new CategoriesViewBuilder()
                        .setId(category.getCategoryId())
                        .setName(category.getName())
                        .setSubcategories(subcategories)
                        .build());

                for (Category subcategory : subcategories) {
                    child.add(new CategoriesViewBuilder()
                            .setId(subcategory.getCategoryId())
                            .setName(subcategory.getName())
                            .setSubcategories(getDAOFactory().getCategoryDAO().getCategoriesByParentId(subcategory.getCategoryId()))
                            .build());
                }
            }
        }

        request.setAttribute("heads", heads);
        request.setAttribute("child", child);

    }
}
