package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

@WebServlet(name = "CategoriesServlet", urlPatterns = {"/CategoriesServlet"})
public class CategoriesServlet extends HttpServlet {

    public class Middles {
        Category head;
        List<Category> categories;

        public Middles(Category head, List<Category> categories) {
            this.head = head;
            this.categories = categories;
        }

        public Category getHead() {
            return head;
        }

        public List<Category> getCategories() {
            return categories;
        }
    }

    public class Categories {
        Category head;
        List<Middles> middles;

        public Category getHead() {
            return head;
        }

        public List<Middles> getMiddles() {
            return middles;
        }

        public Categories(Category head, List<Middles> middles) {

            this.head = head;
            this.middles = middles;
        }
    }

    private List<Middles> getMiddlesCategories(Category category) {
        List<Category> categories = getDAOFactory().getCategoryDAO().getCategoriesByParentId(category.getCategoryId());
        List<Middles> middles = new ArrayList<>();
        for (Category categ : categories) {
            middles.add(new Middles(categ, getDAOFactory().getCategoryDAO().getCategoriesByParentId(categ.getCategoryId())));
        }
        return middles;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> categories = getDAOFactory().getCategoryDAO().getCategories();
        List<Categories> heads = new ArrayList<Categories>();

        for (Category category : categories) {
            if (category.getParentId() == 0) {
                heads.add(new Categories(category, getMiddlesCategories(category)));
            }
        }

        System.out.print(heads);
        request.setAttribute("categories", heads);


    }
}
