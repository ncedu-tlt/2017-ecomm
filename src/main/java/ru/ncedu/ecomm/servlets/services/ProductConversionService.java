package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.servlets.models.*;
import ru.ncedu.ecomm.servlets.models.builders.CompareCharBuilder;
import ru.ncedu.ecomm.servlets.models.builders.ProductItemsViewBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProductConversionService {
    private static final String CHARACTERISTIC_NAME_FOR_IMAGE_URL = "Images";
    private static final String NO_INFORMATION = "No information";

    private static ProductConversionService instance;

    private ProductConversionService() {

    }

    public static synchronized ProductConversionService getInstance() {
        if (instance == null) {
            instance = new ProductConversionService();
        }
        return instance;
    }


    private String getImageUrlForProduct(ProductDetailsModel productDetailsModel) {
        String notClearImageLinks = null;
        for (CharacteristicGroupModel charsGroup : productDetailsModel.getCharacteristicGroupModels()) {
            for (CharacteristicModel characteristic : charsGroup.getCharacteristics()) {
                if (characteristic.getName().equalsIgnoreCase(CHARACTERISTIC_NAME_FOR_IMAGE_URL)) {
                    notClearImageLinks = characteristic.getValue();
                }
            }
        }
        String[] links = notClearImageLinks != null ? notClearImageLinks.split(",") : new String[0];

        return links.length > 0 ? links[0] : null;
    }

    public List<ProductViewModel> convertSourceListToProductViewModelList(List<ProductDetailsModel> sourceList) {
        List<ProductViewModel> resultList = new ArrayList<>();

        if (sourceList != null && sourceList.size() > 0) {
            for (ProductDetailsModel product : sourceList) {
                resultList.add(new ProductItemsViewBuilder()
                        .setCategoryId(product.getCategoryId())
                        .setName(product.getName())
                        .setProductId(product.getId())
                        .setImageUrl(getImageUrlForProduct(product))
                        .setPrice(product.getPrice())
                        .setDiscount(product.getDiscount())
                        .setRating(product.getRating())
                        .build());
            }

        }
        return resultList;
    }

    public List<CompareTabCharGroup> convertSourceListToCharCompareList(List<ProductDetailsModel> sourceList) {
        List<CompareTabCharGroup> charList = new ArrayList();

        if (sourceList != null && sourceList.size() > 0) {
            for (ProductDetailsModel productDetailsModel : sourceList) {
                for (CharacteristicGroupModel characteristicGroupModel : productDetailsModel.getCharacteristicGroupModels()) {
                    if (characteristicGroupModel.getCharacteristics().size() > 0) {
                        CompareTabCharGroup groupForTable = new CompareTabCharGroup();
                        List<CompareChar> chars = new ArrayList<>();
                        groupForTable.setName(characteristicGroupModel.getName());

                        for (CharacteristicModel characteristicModel : characteristicGroupModel.getCharacteristics()) {
                            if (!characteristicModel.getName().equalsIgnoreCase(CHARACTERISTIC_NAME_FOR_IMAGE_URL)) {
                                List<String> charsValue = new ArrayList<>();
                                CompareChar compareChar = new CompareChar();
                                compareChar.setName(characteristicModel.getName());
                                charsValue.add(
                                        characteristicModel.getValue() == null ?
                                                "No Information" :
                                                characteristicModel.getValue()
                                );
                                compareChar.setCharLines(charsValue);
                                chars.add(compareChar);
                            }
                        }

                        groupForTable.setProductChars(chars);
                        charList.add(groupForTable);
                    }
                }
            }
        }
        return charList;
    }


    public List<CompareTabCharGroup> addCharacteristicValueInList(List<CompareTabCharGroup> compareChars, List<ProductDetailsModel> sourceList) {
        cleanCompareTabValue(compareChars);
        for (ProductDetailsModel productDetailsModel : sourceList) {
            for (CharacteristicGroupModel characteristicGroupModel : productDetailsModel.getCharacteristicGroupModels()) {
                for (CharacteristicModel characteristicModel : characteristicGroupModel.getCharacteristics()) {
                    if (!characteristicModel.getName().equalsIgnoreCase(CHARACTERISTIC_NAME_FOR_IMAGE_URL)) {
                        for (CompareTabCharGroup compareTabCharGroup : compareChars) {
                            for (CompareChar compareChar : compareTabCharGroup.getProductChars()) {
                                if (compareTabCharGroup.getName().equalsIgnoreCase(characteristicGroupModel.getName()) &&
                                        characteristicModel.getName().equalsIgnoreCase(compareChar.getName())) {
                                    compareChar.getCharLines().add(characteristicModel.getValue() == null ?
                                            "No Information" :
                                            characteristicModel.getValue());
                                }
                            }
                        }
                    }
                }
            }
        }
        return compareChars;
    }


    private void cleanCompareTabValue(List<CompareTabCharGroup> compareChars) {
        for (CompareTabCharGroup compareTabCharGroup : compareChars) {
            for (CompareChar compareChar : compareTabCharGroup.getProductChars()) {
                List<String> clearList = new ArrayList<>();
                compareChar.setCharLines(clearList);
            }
        }
    }
}
