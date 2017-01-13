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

    public static final String DEFAULT_IMAGE_URL = "/images/defaultimage/image.png";
    public static final long CHARACTERISTIC_ID_FOR_IMAGE_URL = 28;

    private static final int CATEGORY_ID_FOR_BEST_OFFERS = 0;
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
        if (!checkInNull(categoryIdByRequest)) {
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
        return getProductsToView(getProductsById(categoryId));
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

    private List<CharacteristicValue> getImageUrl(long productId) {
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
        return DiscountService
                .getInstance()
                .getDiscountPrice(discountId, price);
    }

    public List<ProductViewModel> getProductsToView (List<Product> products){

        List<ProductViewModel> productsView = new ArrayList<>();

        ProductViewModel itemForView;
        Rating productAverageRating;
        List<CharacteristicValue> characteristicValues;

        for (Product product : products) {
            int productRating = 0;

            String imageUrl = DEFAULT_IMAGE_URL;

            characteristicValues = getImageUrl(product.getId());

            if (!characteristicValues.isEmpty()) {

                imageUrl = getImageUrlByCharacteristicList(characteristicValues);
            }

            productAverageRating = getRating(product.getId());

            if (!checkInNull(productAverageRating)) {
                productRating = productAverageRating.getRaiting();
            }


            itemForView = new ProductItemsViewBuilder()
                    .setProductId(product.getId())
                    .setName(product.getName())
                    .setPrice(product.getPrice())
                    .setImageUrl(imageUrl)
                    .setRating(productRating)
                    .build();

            if (product.getDiscountId() > 1) {
                itemForView.setDiscount(getDiscountPrice(product.getDiscountId(),
                        product.getPrice()));
            }

            productsView.add(itemForView);
        }
        return productsView;
    }

    private String getImageUrlByCharacteristicList(List<CharacteristicValue> characteristicValues) {
        String imageURL = null;

        for (CharacteristicValue characteristicValue : characteristicValues){
            imageURL = characteristicValue.getCharacteristicValue();
        }

        return imageURL;
    }
}
