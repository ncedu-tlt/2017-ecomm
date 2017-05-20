package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.dao.CategoryDAOObject;
import ru.ncedu.ecomm.data.models.dao.CharacteristicValueDAOObject;
import ru.ncedu.ecomm.data.models.dao.ProductDAOObject;
import ru.ncedu.ecomm.data.models.dao.Rating;
import ru.ncedu.ecomm.data.models.dao.builders.CharacteristicValueDAOObjectBuilder;
import ru.ncedu.ecomm.servlets.models.*;
import ru.ncedu.ecomm.servlets.models.builders.CategoryViewBuilder;
import ru.ncedu.ecomm.servlets.models.builders.ProductItemsViewBuilder;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ProductViewService {

    private static final String PRODUCTS_SOURCE = "productSource";
    public static final String DEFAULT_IMAGE_URL = "/images/defaultimage/image.png";
    public static final long CHARACTERISTIC_ID_FOR_IMAGE_URL = 28;
    public static final String IMAGE_URL_CHARACTERISTIC_NAME = "Images";

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

    public List<CategoryViewModel> getBestOffersCategory(HttpSession session) {
        List<CategoryViewModel> bestOffersCategory = new ArrayList<>();

        CategoryViewModel bestOffers = new CategoryViewBuilder()
                .setId(CATEGORY_ID_FOR_BEST_OFFERS)
                .setProducts(getProductForCategory(CATEGORY_ID_FOR_BEST_OFFERS))
                .build();

        if (session.getAttribute(PRODUCTS_SOURCE) != null) {
            bestOffers.setProducts(changeProductInCompare(bestOffers.getProducts(), session));
        }

        bestOffersCategory.add(bestOffers);
        return bestOffersCategory;
    }

    public List<CategoryViewModel> getCategoriesById(List<CategoryDAOObject> categories, HttpSession session) {

        List<CategoryViewModel> categoriesById = new ArrayList<>();

        for (CategoryDAOObject category : categories) {

            CategoryViewModel categoryByRequest = new CategoryViewBuilder()
                    .setId(category.getCategoryId())
                    .setName(category.getName())
                    .setProducts(getProductForCategory(category.getCategoryId()))
                    .build();

            if (session.getAttribute(PRODUCTS_SOURCE) != null) {
                categoryByRequest.setProducts(changeProductInCompare(categoryByRequest.getProducts(), session));
            }

            categoriesById.add(categoryByRequest);
        }
        return categoriesById;
    }

    private List<ProductViewModel> changeProductInCompare(List<ProductViewModel> productViewModels,
                                                          HttpSession session) {
        List<ProductDetailsModel> sourceList = (List<ProductDetailsModel>) session.getAttribute(PRODUCTS_SOURCE);

        for (ProductViewModel productViewModel : productViewModels) {
            for (ProductDetailsModel productDetailsModel : sourceList) {
                if (productViewModel.getId() == productDetailsModel.getId()) {
                    productViewModel.setCompare(true);
                }
            }
        }
        return productViewModels;
    }

    private List<ProductViewModel> getProductForCategory(long categoryId) {

        if (categoryId != CATEGORY_ID_FOR_BEST_OFFERS) {
            return getProductsToView(getProductAllChildrenCategory(categoryId));

        } else {
            return getProductsToView(getProductsById(categoryId));

        }
    }

    private List<ProductDAOObject> getProductsById(long categoryId) {
        if (categoryId == CATEGORY_ID_FOR_BEST_OFFERS) {
            return getDAOFactory().getProductDAO()
                    .getBestOffersProducts();
        } else {
            return getDAOFactory().getProductDAO()
                    .getProductsByCategoryId(categoryId);
        }
    }

    private CharacteristicValueDAOObject getImageUrl(long productId) {
        return getDAOFactory()
                .getCharacteristicValueDAO()
                .getCharacteristicValueByIdAndProductId(productId,
                        CHARACTERISTIC_ID_FOR_IMAGE_URL);
    }

    List<ProductViewModel> getProductModelByOrderId(long orderId) {
        List<ProductDAOObject> productForBuild = DAOFactory.getDAOFactory()
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

    public List<ProductViewModel> getProductsToView(List<ProductDAOObject> products) {

        List<ProductViewModel> productsView = new ArrayList<>();

        ProductViewModel itemForView;
        Rating productAverageRating;
        CharacteristicValueDAOObject characteristicValue;

        for (ProductDAOObject product : products) {
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

    private String getImageUrlByCharacteristicList(CharacteristicValueDAOObject characteristicValue) {
        String imageURL = characteristicValue.getCharacteristicValue();
        if (imageURL != null) {
            String[] links = imageURL.trim().split(",");

            return links[0];
        } else {
            return DEFAULT_IMAGE_URL;
        }
    }

    private List<ProductDAOObject> getProductAllChildrenCategory(long categoryId) {
        return DAOFactory
                .getDAOFactory()
                .getProductDAO()
                .getProductAllChildrenCategory(categoryId);
    }

    public ProductDetailsModel getFullProductById(long productId) {
        ProductDetailsModel product = DAOFactory
                .getDAOFactory()
                .getProductDAO()
                .getFullProductById(productId);

        List<CharacteristicGroupModel> productChars = product.getCharacteristicGroupModels();
        if (productChars.size() != 0) {
            List<CharacteristicGroupModel> productsCharsToRemove = new ArrayList<>();

            for (CharacteristicGroupModel characteristicGroupModel : product.getCharacteristicGroupModels()) {
                if (characteristicGroupModel.getCharacteristics().isEmpty()) {
                    productsCharsToRemove.add(characteristicGroupModel);
                }
            }
            if (productsCharsToRemove.size() != 0) {
                productChars.removeAll(productsCharsToRemove);
            }
        }

        removeImageFromCharsAndAddImage(product);
        return product;
    }

    private void removeImageFromCharsAndAddImage(ProductDetailsModel product) {
        for (CharacteristicGroupModel characteristicGroupModel : product.getCharacteristicGroupModels()) {
            List<String> image = new ArrayList<>();
            Iterator iterator = characteristicGroupModel
                    .getCharacteristics()
                    .iterator();

            while (iterator.hasNext()) {
                CharacteristicModel characteristicModel = (CharacteristicModel) iterator.next();

                if (characteristicModel.getName().equalsIgnoreCase(IMAGE_URL_CHARACTERISTIC_NAME)) {
                    image.add(getImageUrlByCharacteristicList(convertCharacteristic(characteristicModel)));
                    product.setImagesList(image);

                    iterator.remove();
                }
            }
        }
    }

    private CharacteristicValueDAOObject convertCharacteristic(CharacteristicModel characteristicModel) {
        return new CharacteristicValueDAOObjectBuilder()
                .setCharacteristicValue(characteristicModel.getValue())
                .build();
    }

}
