package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.*;
import ru.ncedu.ecomm.servlets.viewmodel.CategoryViewModel;
import ru.ncedu.ecomm.servlets.viewmodel.viewmodelbuilders.CategoryViewBuilder;
import ru.ncedu.ecomm.servlets.viewmodel.viewmodelbuilders.ProductItemsViewBuilder;
import ru.ncedu.ecomm.servlets.viewmodel.ProductItemsView;

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
        browseCategories(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        browseCategories(req, resp);
    }

    private void browseCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<CategoryViewModel> categoryViewModels = getCategoryViewModels(request);

        request.setAttribute("categoriesForView", categoryViewModels);
        request.getRequestDispatcher("/views/pages/category.jsp").forward(request, response);
    }


    private List<CategoryViewModel> getCategoryViewModels(HttpServletRequest request) {

        List<Category> categories = getCategoryListByRequest(request);
        List<CategoryViewModel> viewCategories = new ArrayList<>();

        for (Category category : categories) {

            CategoryViewModel categoryByRequest = new CategoryViewBuilder()
                    .setCategoryId(category.getCategoryId())
                    .setCategoryName(category.getName())
                    .setProductInCategory(addProductToViewByCategoryId(category.getCategoryId()))
                    .setChildCategory(getChildCategories(category.getCategoryId()))
                    .build();

            removeEmptyCategory(categoryByRequest);
            viewCategories.add(categoryByRequest);
        }

        return viewCategories;
    }

    private List<Category> getCategoryListByRequest(HttpServletRequest request) {
        List<Category> categories;
        long categoryId = 0;

        String categoryIdByRequest = request.getParameter("category_id");

        if (categoryIdByRequest != null) {
            categoryId = Long.parseLong(categoryIdByRequest);
        }

        if (categoryIdByRequest != null) {
            categories = new ArrayList<>();

            Category categoryByRequestId = getDAOFactory()
                    .getCategoryDAO()
                    .getCategoryById(categoryId);

            categories.add(categoryByRequestId);
        } else {
            categories = getDAOFactory()
                    .getCategoryDAO()
                    .getParentCategory();
        }

        return categories;
    }

    private Set<CategoryViewModel> getChildCategories(long categoryId) {

        CategoryViewModel categoryForChildList = null;
        Set<CategoryViewModel> allChildCategories = new HashSet<>();

        List<Category> allCategories = getDAOFactory()
                .getCategoryDAO()
                .getCategories();

        for (Category category : allCategories) {

            if (category.getParentId() == categoryId) {
                categoryForChildList = new CategoryViewBuilder()
                        .setCategoryId(category.getCategoryId())
                        .setCategoryName(category.getName())
                        .setProductInCategory(addProductToViewByCategoryId(category.getCategoryId()))
                        .setChildCategory(getChildCategories(category.getCategoryId()))
                        .setParentId(category.getParentId())
                        .build();
            }

            allChildCategories.add(categoryForChildList);
        }
      return allChildCategories;
    }

    private Set<ProductItemsView> addProductToViewByCategoryId(long categoryId) {

        long characteristicIdForImageURL = 28;

        Set<ProductItemsView> notRepeatedItems = new HashSet<>();

        ProductItemsView ItemForView;
        Rating productAvergeRating;
        CharacteristicValue characteristicValue;

        List<Product> products = getDAOFactory()
                .getProductDAO()
                .getProductsByCategoryId(categoryId);

        String imageUrl = "/images/defaultimage/image.png";

        for (Product product : products) {
            int productRating = 0;

            characteristicValue = getDAOFactory()
                    .getCharacteristicValueDAO()
                    .getCharacteristicValueByIdAndProductId(product.getId(),
                            characteristicIdForImageURL);

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
                    .setDiscount(getDiscountValue(product.getDiscountId()))
                    .setImageUrl(imageUrl)
                    .setRating(productRating)
                    .build();

            notRepeatedItems.add(ItemForView);
        }

        return notRepeatedItems;
    }

    private int getDiscountValue(long discountId) {
        int discountValue = 0;

        List<Discount> allDiscountValues = getDAOFactory()
                .getDiscountDAO()
                .getDiscount();

        for (Discount discount : allDiscountValues) {
            if (discount.getDiscountId() == discountId) {
                discountValue = discount.getValue();
            }
        }

        return discountValue;
    }

    private void removeEmptyCategory(CategoryViewModel categoryViewModel) {

        Iterator categoryIterator = categoryViewModel.getChildCategory().iterator();

        while (categoryIterator.hasNext()) {
            CategoryViewModel collectionItem = (CategoryViewModel) categoryIterator.next();

            if (checkForRemoving(collectionItem)) {
                categoryIterator.remove();
            }

        }
    }

    private boolean checkForRemoving(CategoryViewModel collectionItem) {
        return collectionItem != null &&
                collectionItem.getProductInCategory().size() == 0 &&
                collectionItem.getChildCategory().size() < 2;
    }
}