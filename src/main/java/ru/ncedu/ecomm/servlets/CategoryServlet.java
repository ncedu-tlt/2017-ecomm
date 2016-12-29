package ru.ncedu.ecomm.servlets;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import ru.ncedu.ecomm.data.models.*;
import ru.ncedu.ecomm.data.models.builders.ProductItemsViewBuilder;

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

        Set<ProductItemsView> productToView = addProductToView();
        List<Category> categoriesForView = categoriesFilter(getCategoryByParametr(request), productToView);


        request.setAttribute("categoriesForView", categoriesForView);
        request.setAttribute("productToView", productToView);
        request.getRequestDispatcher("/views/pages/category.jsp").forward(request, response);
    }

    private List<Category> categoriesFilter(List<Category> categoriesForView, Set<ProductItemsView> products) {
        HashSet<Category> categoriesSet = new HashSet<>();
        List<Category> filteringCategory = new ArrayList<>();


        for (Category category : categoriesForView) {
            if (category.getParentId() == 0) {
                filteringCategory.add(category);
            }
            for (ProductItemsView product : products) {
                if (product.getCategoryId() == category.getCategoryId()) {
                    categoriesSet.add(category);
                }
            }
        }

        filteringCategory.addAll(categoriesSet);

        filteringCategory.sort((categoryOne, categoryTwo) -> {
            if (categoryOne.getCategoryId() > categoryTwo.getCategoryId()) {
                return 1;
            } else if (categoryOne.getCategoryId() == categoryTwo.getCategoryId()) {
                return 0;
            } else {
                return -1;
            }
        });


        return filteringCategory;
    }

    private HashSet<ProductItemsView> addProductToView() {

        final int CHARACTERISTIC_ID_FOR_ONE_CATEGORY = 28;
        final int CHARACTERISTIC_ID_FOR_FIVE_CATEGORY = 29;

        HashSet<ProductItemsView> productItemsViewList = new HashSet<>();
        ProductItemsView ItemForView = null;
        Rating productAvergeRating;
        CharacteristicValue characteristicValue;

        List<Product> products = getDAOFactory()
                .getProductDAO()
                .getProducts();

        long characteristicId = CHARACTERISTIC_ID_FOR_ONE_CATEGORY;

        for (Product product : products) {

            String imageUrl = "\\images\\defaultimage\\image.png";
            int productRating = 0;

            if (product.getCategoryId() == 5) {
                characteristicId = CHARACTERISTIC_ID_FOR_FIVE_CATEGORY;
            }

            characteristicValue = getDAOFactory()
                    .getCharacteristicValueDAO()
                    .getCharacteristicValueByIdAndProductId(product.getId(),
                            characteristicId);

            if (characteristicValue != null) {
                imageUrl = characteristicValue.getCharacteristicValue();
            }

            productAvergeRating = getDAOFactory()
                    .getReviewDAO()
                    .getAverageRatingByProductId(product.getId());

            if (productAvergeRating != null) {
                productRating = productAvergeRating.getRaiting();
            }


            ItemForView = new ProductItemsViewBuilder()
                    .setProductId(product.getId())
                    .setCategoryId(product.getCategoryId())
                    .setName(product.getName())
                    .setPrice(product.getPrice())
                    .setDiscountId(product.getDiscountId())
                    .setImageUrl(imageUrl)
                    .setRating(productRating)
                    .build();

            productItemsViewList.add(ItemForView);
        }

        return productItemsViewList;
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