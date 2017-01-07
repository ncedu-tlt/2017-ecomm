package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.*;
import ru.ncedu.ecomm.servlets.models.CategoryViewModel;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;
import ru.ncedu.ecomm.servlets.models.builders.CategoryViewBuilder;
import ru.ncedu.ecomm.servlets.models.builders.ProductItemsViewBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ProductViewService {

    private static final long CHARACTERISTIC_ID_FOR_IMAGE_URL = 28;
    private static final String DEFAULT_IMAGE_URL = "/images/defaultimage/image.png";

    private ProductViewService(){}

    private static ProductViewService instance;

    public static synchronized ProductViewService getInstance() {
        if (instance == null) {
            instance = new ProductViewService();
        }
        return instance;
    }
    public List<CategoryViewModel> getCategoryViewModels(HttpServletRequest request) {

        List<Category> categories = getCategoryListByRequest(request);
        List<CategoryViewModel> viewCategories = new ArrayList<>();

        for (Category category : categories) {

            CategoryViewModel categoryByRequest = new CategoryViewBuilder()
                    .setId(category.getCategoryId())
                    .setName(category.getName())
                    .setProducts(addProductToViewByCategoryId(category.getCategoryId()))
                    .build();

            viewCategories.add(categoryByRequest);
        }

        return viewCategories;
    }

    private List<Category> getCategoryListByRequest(HttpServletRequest request) {
        List<Category> categories;

        String categoryIdByRequest = request.getParameter("category_id");

        if (!checkInNull(categoryIdByRequest)) {

            categories = getCategoriesById(
                    getCategoryId(categoryIdByRequest)
            );
        } else {
            categories = getCategories();
        }
        return categories;
    }

    private long getCategoryId(String categoryIdByRequest) {
        return Long.parseLong(categoryIdByRequest);
    }

    private List<Category> getCategoriesById(long categoryId) {
        return getDAOFactory()
                .getCategoryDAO()
                .getAllNotEmptyChildrenCategoryById(categoryId);
    }

    private List<Category> getCategories() {
        return getDAOFactory()
                .getCategoryDAO()
                .getAllNotEmptyCategory();
    }


    private List<ProductViewModel> addProductToViewByCategoryId(long categoryId) {

        List<ProductViewModel> productsInCategory = new ArrayList<>();

        List<Product> products = getProductsById(categoryId);

        ProductViewModel ItemForView;
        Rating productAvergeRating;
        CharacteristicValue characteristicValue;


        for (Product product : products) {
            int productRating = 0;

            String imageUrl = DEFAULT_IMAGE_URL;

            characteristicValue = getImageUrl(product.getId());

            if (!checkInNull(characteristicValue)) {
                imageUrl = characteristicValue.getCharacteristicValue();
            }

            productAvergeRating = getRating(product.getId());

            if (!checkInNull(productAvergeRating)) {
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

            productsInCategory.add(ItemForView);
        }

        return productsInCategory;
    }

    private boolean checkInNull(Object object) {
        return object == null;
    }

    private List<Product> getProductsById(long categoryId) {
        return getDAOFactory().getProductDAO()
                .getProductsByCategoryId(categoryId);
    }

    private CharacteristicValue getImageUrl(long productId) {
        return getDAOFactory()
                .getCharacteristicValueDAO()
                .getCharacteristicValueByIdAndProductId(productId,
                        CHARACTERISTIC_ID_FOR_IMAGE_URL);
    }

    private Rating getRating(long productId) {
        return getDAOFactory()
                .getReviewDAO()
                .getAverageRatingByProductId(productId);
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
}
