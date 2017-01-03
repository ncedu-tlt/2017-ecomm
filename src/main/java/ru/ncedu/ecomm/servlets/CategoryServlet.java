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
        itemView(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        itemView(req, resp);
    }

    private void itemView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                    .setChildCategory(addAllChildCategory(category.getCategoryId()))
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

    private List<CategoryViewModel> addAllChildCategory(long categoryId) {

        CategoryViewModel categoryToChildList = null;

        Set<CategoryViewModel> childCategory = new HashSet<>();
        List<CategoryViewModel> categoryViewModels = new ArrayList<>();

        List<Category> allCategories = getDAOFactory()
                .getCategoryDAO()
                .getCategories();

        for (Category category : allCategories) {

            if (category.getParentId() == categoryId) {
                categoryToChildList = new CategoryViewBuilder()
                        .setCategoryId(category.getCategoryId())
                        .setCategoryName(category.getName())
                        .setProductInCategory(addProductToViewByCategoryId(category.getCategoryId()))
                        .setChildCategory(addAllChildCategory(category.getCategoryId()))
                        .setParentId(category.getParentId())
                        .build();
            }

            childCategory.add(categoryToChildList);
        }

        categoryViewModels.addAll(childCategory);

        return categoryViewModels;
    }

    private Set<ProductItemsView> addProductToViewByCategoryId(long categoryId) {

        long characteristicId = 28;

        Set<ProductItemsView> notRepeatedItems = new HashSet<>();

        ProductItemsView ItemForView;
        Rating productAvergeRating;
        CharacteristicValue characteristicValue;

        List<Product> products = getDAOFactory()
                .getProductDAO()
                .getProductsByCategoryId(categoryId);

        for (Product product : products) {

            String imageUrl = "/images/defaultimage/image.png";
            int productRating = 0;

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