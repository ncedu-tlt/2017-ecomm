package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.servlets.models.CharacteristicGroupModel;
import ru.ncedu.ecomm.servlets.models.CharacteristicModel;
import ru.ncedu.ecomm.servlets.models.ProductDetailsModel;
import ru.ncedu.ecomm.servlets.models.ProductViewModel;
import ru.ncedu.ecomm.servlets.models.builders.ProductItemsViewBuilder;

public class ProductConversionService {
    private static final String CHARACTERISTIC_NAME_FOR_IMAGE_URL = "Images";

    private static ProductConversionService instance;

    private ProductConversionService(){

    }

    public static synchronized ProductConversionService getInstance(){
        if (instance == null){
            instance = new ProductConversionService();
        }
        return instance;
    }


    public ProductViewModel convertProductDetailsToProductViewModel(ProductDetailsModel productDetailsModel){
        return new ProductItemsViewBuilder()
                .setProductId(productDetailsModel.getId())
                .setCategoryId(productDetailsModel.getCategoryId())
                .setName(productDetailsModel.getName())
                .setDiscount(productDetailsModel.getDiscount())
                .setPrice(productDetailsModel.getPrice())
                .setRating(productDetailsModel.getRating())
                .setImageUrl(getImageUrlForProduct(productDetailsModel))
                .build();
    }

    private String getImageUrlForProduct(ProductDetailsModel productDetailsModel) {
        String notClearImageLinks = null;
        for (CharacteristicGroupModel charsGroup : productDetailsModel.getCharacteristicGroupModels()){
            for (CharacteristicModel characteristic : charsGroup.getCharacteristics()){
                if (characteristic.getName().equalsIgnoreCase(CHARACTERISTIC_NAME_FOR_IMAGE_URL)){
                    notClearImageLinks = characteristic.getValue();
                }
            }
        }
        String[] links = notClearImageLinks != null ? notClearImageLinks.split(",") : new String[0];

        return links.length > 0 ? links[0] : null;
    }
}
