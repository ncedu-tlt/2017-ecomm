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
    private static final int CATEGORY_ID_FOR_BEST_OFFERS = 0;
    private static final String DEFAULT_IMAGE_URL = "/images/defaultimage/image.png";
    private static final String HOME_PAGE_URL = "/home";

    private ProductViewService() {
    }

    private static ProductViewService instance;

    public static synchronized ProductViewService getInstance() {
        if (instance == null) {
            instance = new ProductViewService();
        }
        return instance;
    }

    public List<CategoryViewModel> getCategoryViewModels(HttpServletRequest request) {

        List<CategoryViewModel> viewCategories;

        if (request.getServletPath().equalsIgnoreCase(HOME_PAGE_URL)) {

             viewCategories = getBestOffersCategory();
        } else {

            viewCategories = getCategoriesById(request);
        }
        return viewCategories;
    }

    private long getCategoryId(String categoryIdByRequest) {
        if (categoryIdByRequest != null) {
            return Long.parseLong(categoryIdByRequest);
        } else {
            return 0;
        }
    }

    private List<CategoryViewModel> getBestOffersCategory() {
        List<CategoryViewModel> bestOffersCategory = new ArrayList<>();

        CategoryViewModel bestOffers = new CategoryViewBuilder()
                .setName("Best Offers")
                .setId(CATEGORY_ID_FOR_BEST_OFFERS)
                .setProducts(addProductToViewByCategoryId(CATEGORY_ID_FOR_BEST_OFFERS))
                .build();

        bestOffersCategory.add(bestOffers);
        return bestOffersCategory;
    }

    private List<CategoryViewModel> getCategoriesById(HttpServletRequest request) {

        List<CategoryViewModel> categoriesById = new ArrayList<>();

        List<Category> categories = getCategoryListByRequest(request);

        for (Category category : categories) {

            CategoryViewModel categoryByRequest = new CategoryViewBuilder()
                    .setId(category.getCategoryId())
                    .setName(category.getName())
                    .setProducts(addProductToViewByCategoryId(category.getCategoryId()))
                    .build();

            categoriesById.add(categoryByRequest);
        }
        return categoriesById;
    }


    private List<Category> getCategoryListByRequest(HttpServletRequest request) {
        List<Category> categories;
        String categoryIdByRequest = request.getParameter("category_id");

        if (checkInNull(categoryIdByRequest) || getCategoryId(categoryIdByRequest) == 0) {

            categories = getCategories();
        } else {

            categories = getCategoriesById(
                    getCategoryId(categoryIdByRequest)
            );
        }
        return categories;
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

        ProductViewModel itemForView;
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


            itemForView = new ProductItemsViewBuilder()
                    .setProductId(product.getId())
                    .setName(product.getName())
                    .setPrice(product.getPrice())
                    .setImageUrl(imageUrl)
                    .setRating(productRating)
                    .build();

            if (product.getDiscountId() > 1){
                itemForView.setDiscount(getDiscountPrice(product.getDiscountId(),
                        product.getPrice()));
            }

            productsInCategory.add(itemForView);
        }

        return productsInCategory;
    }

    private boolean checkInNull(Object object) {
        return object == null;
    }

    private List<Product> getProductsById(long categoryId) {
        if (categoryId == CATEGORY_ID_FOR_BEST_OFFERS) {
            return getDAOFactory().getProductDAO()
                    .getBestOffersProducts();
        } else {
            return getDAOFactory().getProductDAO()
                    .getProductsByCategoryId(categoryId);
        }
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

    private long getDiscountPrice(long discountId, long price){
        double discountValue = price * (getDiscountValue(discountId) / 100.0);

        long discount = (long) discountValue;

        return price - discount;
    }

    private int getDiscountValue(long discountId) {
     List<Discount> allDiscountValues = getDAOFactory()
                .getDiscountDAO()
                .getDiscount();

        for (Discount discount : allDiscountValues) {
            if (discount.getDiscountId() == discountId) {
                return discount.getValue();
            }
        }

        return 0;
    }
}
