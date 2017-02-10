package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.Category;
import ru.ncedu.ecomm.data.models.CharacteristicValue;
import ru.ncedu.ecomm.data.models.Product;
import ru.ncedu.ecomm.data.models.Rating;
import ru.ncedu.ecomm.data.models.builders.CategoryBuilder;
import ru.ncedu.ecomm.servlets.models.CategoryViewModel;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;
import ru.ncedu.ecomm.servlets.models.builders.CategoryViewBuilder;
import ru.ncedu.ecomm.servlets.models.builders.ProductItemsViewBuilder;

import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ProductViewService {

    public static final String DEFAULT_IMAGE_URL = "/images/defaultimage/image.png";
    public static final long CHARACTERISTIC_ID_FOR_IMAGE_URL = 28;

    private static final String NAME_FOR_BEST_OFFERS_CATEGORY = "Best Offers"; //TODO: в JSP
    private static final Long CATEGORY_ID_FOR_BEST_OFFERS = 0L; //TODO: не нужно

    private ProductViewService() {
    }

    private static ProductViewService instance;

    public static synchronized ProductViewService getInstance() {
        if (instance == null) {
            instance = new ProductViewService();
        }
        return instance;
    }

    public List<CategoryViewModel> getBestOffersCategory() {
        List<CategoryViewModel> bestOffersCategory = new ArrayList<>();

        CategoryViewModel bestOffers = new CategoryViewBuilder()
                .setName(NAME_FOR_BEST_OFFERS_CATEGORY)
                .setId(CATEGORY_ID_FOR_BEST_OFFERS)
                .setProducts(addProductToViewByCategoryId(
                        new CategoryBuilder()
                                .setCategoryId(CATEGORY_ID_FOR_BEST_OFFERS) //TODO: а не проще было сделать так, чтобы метод принимал идентификатор?
                                .build())
                )
                .build();

        bestOffersCategory.add(bestOffers);
        return bestOffersCategory;
    }

    public List<CategoryViewModel> getCategoriesById(List<Category> categories) {

        List<CategoryViewModel> categoriesById = new ArrayList<>();

        for (Category category : categories) {

            CategoryViewModel categoryByRequest = new CategoryViewBuilder()
                    .setId(category.getCategoryId())
                    .setName(category.getName())
                    .setProducts(addProductToViewByCategoryId(category))
                    .build();

            categoriesById.add(categoryByRequest);
        }
        return categoriesById;
    }

    //TODO: почему называется add, если это получение?
    //TODO: уточнения в духе byCategoryId нужны только тогда, когда может возникнуть недопонимание. В большинстве случаев, достаточно самих параметров
    private List<ProductViewModel> addProductToViewByCategoryId(Category category) {

        if (category.getParentId() == 0 && category.getCategoryId() != CATEGORY_ID_FOR_BEST_OFFERS) {
            return getProductsToView(getProductAllChildrenCategory(category.getCategoryId()));

        } else {
            return getProductsToView(getProductsById(category.getCategoryId()));

        }
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

    public List<ProductViewModel> getProductModelByOrderId(long orderId){
       List<Product> productForBuild = DAOFactory.getDAOFactory()
                .getProductDAO()
                .getProductByOrderId(orderId);

       return getProductsToView(productForBuild);
    }

    private Rating getRating(long productId) {
        return getDAOFactory()
                .getReviewDAO()
                .getAverageRatingByProductId(productId);
    }

    private long getDiscountPrice(long discountId, long price) {
        return DiscountService
                .getInstance()
                .getDiscountPrice(discountId, price);
    }

    public List<ProductViewModel> getProductsToView(List<Product> products) {

            List<ProductViewModel> productsView = new ArrayList<>();

            ProductViewModel itemForView;
            Rating productAverageRating;
            CharacteristicValue characteristicValue;

            for (Product product : products) {
                int productRating = 0;

                String imageUrl = DEFAULT_IMAGE_URL;

                characteristicValue = getImageUrl(product.getId());

                if (characteristicValue != null) {

                    imageUrl = getImageUrlByCharacteristicList(characteristicValue);
                }

                productAverageRating = getRating(product.getId());

                if (productAverageRating != null) {
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

    private String getImageUrlByCharacteristicList(CharacteristicValue characteristicValue) {
        String imageURL = characteristicValue.getCharacteristicValue();
        String[] links = imageURL.trim().split(",");

        return links[0];
    }

    private List<Product> getProductAllChildrenCategory(long categoryId) {
        return DAOFactory
                .getDAOFactory()
                .getProductDAO()
                .getProductAllChildrenCategory(categoryId);
    }
}
