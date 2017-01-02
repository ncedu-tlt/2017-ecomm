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

        CategoryViewModel categoryViewModels = getCategoryViewModels(request);

        request.setAttribute("categoriesForView", categoryViewModels);
        request.getRequestDispatcher("/views/pages/category.jsp").forward(request, response);
    }


    private CategoryViewModel getCategoryViewModels(HttpServletRequest request) {
        long categoryId = Long.parseLong(request.getParameter("category_id"));

        Category categoryByRequestId = getDAOFactory()
                .getCategoryDAO()
                .getCategoryById(categoryId);

        CategoryViewModel categoryByRequest = new CategoryViewBuilder()
                .setCategoryId(categoryByRequestId.getCategoryId())
                .setCategoryName(categoryByRequestId.getName())
                .setProductInCategory(addProductToViewByCategoryId(categoryId))
                .setChildCategory(addAllChildCategory(categoryId))
                .build();

        removeEmptyCategory(categoryByRequest);
        sortingDataInCategory(categoryByRequest);

        return categoryByRequest;
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

    private List<ProductItemsView> addProductToViewByCategoryId(long categoryId) {

        final int CHARACTERISTIC_ID_FOR_ONE_CATEGORY = 28;
        final int CHARACTERISTIC_ID_FOR_FIVE_CATEGORY = 29;

        Set<ProductItemsView> productItemsViewList = new HashSet<>();
        List<ProductItemsView> productItemsViews = new ArrayList<>();

        ProductItemsView ItemForView;
        Rating productAvergeRating;
        CharacteristicValue characteristicValue;

        List<Product> products = getDAOFactory()
                .getProductDAO()
                .getProductsByCategoryId(categoryId);

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
                    .setDiscount(getDiscountValue(product.getDiscountId()))
                    .setImageUrl(imageUrl)
                    .setRating(productRating)
                    .build();

            productItemsViewList.add(ItemForView);
        }

        productItemsViews.addAll(productItemsViewList);

        return productItemsViews;
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

            if (collectionItem != null &&
                    collectionItem.getProductInCategory().size() == 0 &&
                    collectionItem.getChildCategory().size() < 2) {
                categoryIterator.remove();
            }

        }
    }

    private void sortingDataInCategory(CategoryViewModel categoryByRequest) {
        Comparator categoryComparator = (objectOne, objectTwo) -> {

            if (objectOne != null && objectTwo != null) {
                CategoryViewModel firstCategory = (CategoryViewModel) objectOne;
                CategoryViewModel secondCategory = (CategoryViewModel) objectTwo;

                if (firstCategory.getCategoryId() > secondCategory.getCategoryId()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        };
        Comparator productComparator = (objectOne, objectTwo) -> {

            if (objectOne != null && objectTwo != null) {
                ProductItemsView firstProduct = (ProductItemsView) objectOne;
                ProductItemsView secondProduct = (ProductItemsView) objectTwo;

                if (firstProduct.getPrice() > secondProduct.getPrice()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        };

        if (categoryByRequest.getChildCategory().size() != 0) {
            (categoryByRequest.getChildCategory()).sort(categoryComparator);
        }
        if (categoryByRequest.getProductInCategory().size() != 0) {
            (categoryByRequest.getProductInCategory()).sort(productComparator);
        }

        for (CategoryViewModel categoryViewModel : categoryByRequest.getChildCategory()) {
            if (categoryViewModel != null) {
                (categoryViewModel.getProductInCategory()).sort(productComparator);
            }
        }
    }
}