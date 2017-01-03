package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.models.*;
import ru.ncedu.ecomm.servlets.models.CategoryViewModel;
import ru.ncedu.ecomm.servlets.models.builders.CategoryViewBuilder;
import ru.ncedu.ecomm.servlets.models.builders.ProductItemsViewBuilder;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;

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

    private final long CHARACTERISTIC_ID_FOR_IMAGE_URL = 28;
    private final String DEFAULT_IMAGE_URL = "/images/defaultimage/image.png";

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
                    .setId(category.getCategoryId())
                    .setName(category.getName())
                    .setProducts(addProductToViewByCategoryId(category.getCategoryId()))
                    .setCategories(getChildCategories(category.getCategoryId()))
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
                        .setId(category.getCategoryId())
                        .setName(category.getName())
                        .setProducts(addProductToViewByCategoryId(category.getCategoryId()))
                        .setCategories(getChildCategories(category.getCategoryId()))
                        .build();
            }

            allChildCategories.add(categoryForChildList);
        }
        return allChildCategories;
    }

    private Set<ProductViewModel> addProductToViewByCategoryId(long categoryId) {

        Set<ProductViewModel> notRepeatedItems = new HashSet<>();

        ProductViewModel ItemForView;
        Rating productAvergeRating;
        CharacteristicValue characteristicValue;

        List<Product> products = getDAOFactory()
                .getProductDAO()
                .getProductsByCategoryId(categoryId);


        for (Product product : products) {

            String imageUrl = DEFAULT_IMAGE_URL;
            int productRating = 0;

            characteristicValue = getDAOFactory()
                    .getCharacteristicValueDAO()
                    .getCharacteristicValueByIdAndProductId(product.getId(),
                            CHARACTERISTIC_ID_FOR_IMAGE_URL);

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

        Iterator categoryIterator = categoryViewModel.getCategories().iterator();

        while (categoryIterator.hasNext()) {
            CategoryViewModel collectionItem = (CategoryViewModel) categoryIterator.next();

            if (checkForRemoving(collectionItem)) {
                categoryIterator.remove();
            }

        }
    }

    private boolean checkForRemoving(CategoryViewModel collectionItem) {
        return collectionItem != null &&
                collectionItem.getProducts().isEmpty() &&
                collectionItem.getCategories().size() < 2;
    }
}