package ru.ncedu.ecomm.servlets;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.*;
import ru.ncedu.ecomm.servlets.models.*;
import ru.ncedu.ecomm.servlets.models.builders.CharacteristicGroupModelBuilder;
import ru.ncedu.ecomm.servlets.models.builders.CharacteristicModelBuilder;
import ru.ncedu.ecomm.servlets.models.builders.ProductDetailsModelBuilder;
import ru.ncedu.ecomm.servlets.services.DiscountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.ncedu.ecomm.servlets.services.ProductViewService.CHARACTERISTIC_ID_FOR_IMAGE_URL;
import static ru.ncedu.ecomm.servlets.services.ProductViewService.DEFAULT_IMAGE_URL;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

    private static final long DEFAULT_CATEGORY_ID = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        browseProduct(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // handle operations
        browseProduct(req, resp);
    }

    private void browseProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long productId = changeProductId(request, response);

        ProductDetailsModel browseProduct = getProductToView(productId);

        request.setAttribute("browseProduct", browseProduct);
        request.getRequestDispatcher("/views/pages/product.jsp").forward(request, response);
    }

    private ProductDetailsModel getProductToView(long productId) {

        Product productForBuilding = getProductForBuild(productId);
        Rating avergeRating = getAvergeRating(productId);

        ProductDetailsModel productDetailsModel = new ProductDetailsModelBuilder()
                .setRating(avergeRating.getRaiting())
                .setId(productForBuilding.getId())
                .setPrice(productForBuilding.getPrice())
                .setName(productForBuilding.getName())
                .setDescription(productForBuilding.getDescription())
                .setCharacteristicGroupModels(getCharacteristicGroupModel(
                        productForBuilding.getId()))
                .setProductImages(getImageLinkByProductId(productId))
                .build();

        if (productForBuilding.getDiscountId() > 1) {
            productDetailsModel.setDiscount(
                    getDiscountValue(productForBuilding
                                    .getDiscountId(),
                            productForBuilding
                                    .getPrice()));
        }
        return productDetailsModel;
    }

    private long changeProductId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long productId = 0;

        String categoryIdByRequest = request.getParameter("product_id");

        if (categoryIdByRequest == null) {
            request.setAttribute("category_id", DEFAULT_CATEGORY_ID);
            request.getRequestDispatcher("/views/pages/category.jsp").forward(request, response);

        } else {
            productId = Long.parseLong(categoryIdByRequest);

        }
        return productId;
    }

    private Rating getAvergeRating(long productId) {
        return DAOFactory
                .getDAOFactory()
                .getReviewDAO()
                .getAverageRatingByProductId(productId);
    }

    private Product getProductForBuild(long productId) {
        return DAOFactory
                .getDAOFactory()
                .getProductDAO()
                .getProductById(productId);
    }
    private List<CharacteristicGroupModel> getCharacteristicGroupModel(long productId) {

        CharacteristicGroupModel characteristicGroupModel;

        List<CharacteristicGroupModel> characteristicGroupModels = new ArrayList<>();
        List<CharacteristicGroup> characteristicGroups = getCharacteristicGroup();

        for (CharacteristicGroup characteristicGroup : characteristicGroups) {

            characteristicGroupModel = new CharacteristicGroupModelBuilder()
                    .setCharacteristicGroupName(characteristicGroup.getCharacteristicGroupName())
                    .setCharacteristics(getCharacteristicModelByGroup(
                            characteristicGroup.getCharacteristicGroupId(),
                            productId))

                    .build();


            if (!characteristicGroupModel.getCharacteristics().isEmpty()) {
                characteristicGroupModels.add(characteristicGroupModel);
            }
        }

        return characteristicGroupModels;
    }

    private List<CharacteristicGroup> getCharacteristicGroup() {
        return DAOFactory
                .getDAOFactory()
                .getCharacteristicGroupDAO()
                .getCharacteristicGroup();
    }

    private List<String> getImageLinkByProductId(long productId) {
        List<String> imagesList = new ArrayList<>();

        CharacteristicValue characteristicValue = DAOFactory
                .getDAOFactory()
                .getCharacteristicValueDAO()
                .getCharacteristicValueByIdAndProductId(
                        productId,
                        CHARACTERISTIC_ID_FOR_IMAGE_URL
                );

        if (characteristicValue != null ) {
            String imageLink = characteristicValue.getCharacteristicValue();
            String links[] = imageLink.trim().split(",");

            Collections.addAll(imagesList, links);

        }else {
            imagesList.add(DEFAULT_IMAGE_URL);
        }
        return imagesList;
    }

    private List<CharacteristicModel> getCharacteristicModelByGroup(long characteristicGroupId, long productId) {

        List<CharacteristicModel> characteristicModels = new ArrayList<>();
        List<Characteristic> characteristics = getCharacteristicsByGroupId(characteristicGroupId);
        List<CharacteristicValue> characteristicValues = getCharacteristicValuesByProductId(productId);

        for (Characteristic characteristic : characteristics) {
            CharacteristicModel characteristicModel = getCharacteristicModel(
                    characteristic,
                    characteristicValues
            );
            if (characteristicModel != null &&
                    characteristic.getCharacteristicId() != CHARACTERISTIC_ID_FOR_IMAGE_URL) {
                characteristicModels.add(characteristicModel);
            }
        }

        return characteristicModels;
    }


    private CharacteristicModel getCharacteristicModel(
            Characteristic characteristic,
            List<CharacteristicValue> characteristicValues) {

        CharacteristicModel characteristicModel = null;

        for (CharacteristicValue characteristicValue : characteristicValues) {
            if (characteristic.getCharacteristicId() == characteristicValue.getCharacteristicId()) {
                characteristicModel = new CharacteristicModelBuilder()
                        .setName(characteristic.getCharacteristicName())
                        .setValue(characteristicValue.getCharacteristicValue())
                        .build();

            }
        }

        return characteristicModel;
    }

    private List<Characteristic> getCharacteristicsByGroupId(long characteristicGroupId) {
        return DAOFactory
                .getDAOFactory()
                .getChracteristicDAO()
                .getCharacteristicByGroupId(characteristicGroupId);
    }

    private List<CharacteristicValue> getCharacteristicValuesByProductId(long productId) {
        return DAOFactory
                .getDAOFactory()
                .getCharacteristicValueDAO()
                .getCharacteristicValueByProductId(productId);
    }

    private long getDiscountValue(long discountId, long price) {
        return DiscountService
                .getInstance()
                .getDiscountPrice(discountId, price);
    }




}
