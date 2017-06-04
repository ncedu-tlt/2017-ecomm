package ru.ncedu.ecomm.services;

import ru.ncedu.ecomm.data.DAOFactory;
import ru.ncedu.ecomm.data.models.dao.CharacteristicDAOObject;
import ru.ncedu.ecomm.data.models.dao.CharacteristicGroupDAOObject;
import ru.ncedu.ecomm.data.models.dao.CharacteristicValueDAOObject;
import ru.ncedu.ecomm.data.models.dao.ProductDAOObject;
import ru.ncedu.ecomm.data.models.dto.CharacteristicDTObject;
import ru.ncedu.ecomm.data.models.dto.CharacteristicGroupDTObj;
import ru.ncedu.ecomm.data.models.dto.ProductDTObject;
import ru.ncedu.ecomm.data.models.dto.builders.CharacteristicDTObjectBuilder;
import ru.ncedu.ecomm.data.models.dto.builders.CharacteristicGroupDTObjBuilder;
import ru.ncedu.ecomm.data.models.dto.builders.ProductDTObjectBuilder;

import java.util.ArrayList;
import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;
import static ru.ncedu.ecomm.servlets.services.ProductViewService.CHARACTERISTIC_ID_FOR_IMAGE_URL;

public class RestProductDTO {
    public static List<ProductDTObject> getAllChrildrenProductsByCategoryIdForRest(long categoryId) {
        List<ProductDTObject> productsDTO = new ArrayList<>();
        List<ProductDAOObject> products = getDAOFactory().getProductDAO().getAllChrildrenProductsByCategoryId(categoryId);
        for (ProductDAOObject product : products) {
            ProductDTObject productDTO = new ProductDTObjectBuilder()
                    .setProductId(product.getId())
                    .setCategoryId(product.getCategoryId())
                    .setName(product.getName())
                    .setDescription(product.getDescription())
                    .setPrice(product.getPrice())
                    .setDiscountId(product.getDiscountId())
                    .setCharacteristicGroups(getCharacteristicGroupModel(product.getId()))
                    .build();
            productsDTO.add(productDTO);
        }
        return productsDTO;
    }

    private static List<CharacteristicGroupDTObj> getCharacteristicGroupModel(long productId) {

        CharacteristicGroupDTObj characteristicGroupModel;

        List<CharacteristicGroupDTObj> characteristicGroupModels = new ArrayList<>();
        List<CharacteristicGroupDAOObject> characteristicGroups = getCharacteristicGroup();

        for (CharacteristicGroupDAOObject characteristicGroup : characteristicGroups) {

            characteristicGroupModel = new CharacteristicGroupDTObjBuilder()
                    .setCharacteristicGroupId(characteristicGroup.getCharacteristicGroupId())
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

    private static List<CharacteristicDTObject> getCharacteristicModelByGroup(long characteristicGroupId, long productId) {

        List<CharacteristicDTObject> characteristicModels = new ArrayList<>();
        List<CharacteristicDAOObject> characteristics = getCharacteristicsByGroupId(characteristicGroupId);
        List<CharacteristicValueDAOObject> characteristicValues = getCharacteristicValuesByProductId(productId);

        for (CharacteristicDAOObject characteristic : characteristics) {
            CharacteristicDTObject characteristicModel = getCharacteristicModel(
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

    private static CharacteristicDTObject getCharacteristicModel(
            CharacteristicDAOObject characteristic,
            List<CharacteristicValueDAOObject> characteristicValues) {

        CharacteristicDTObject characteristicModel = null;

        for (CharacteristicValueDAOObject characteristicValue : characteristicValues) {
            if (characteristic.getCharacteristicId() == characteristicValue.getCharacteristicId()) {
                characteristicModel = new CharacteristicDTObjectBuilder()
                        .setCharacteristicId(characteristic.getCharacteristicId())
                        .setName(characteristic.getCharacteristicName())
                        .setValue(characteristicValue.getCharacteristicValue())
                        .build();
            }
        }

        return characteristicModel;
    }

    private static List<CharacteristicGroupDAOObject> getCharacteristicGroup() {
        return DAOFactory
                .getDAOFactory()
                .getCharacteristicGroupDAO()
                .getCharacteristicGroup();
    }

    private static List<CharacteristicDAOObject> getCharacteristicsByGroupId(long characteristicGroupId) {
        return DAOFactory
                .getDAOFactory()
                .getChracteristicDAO()
                .getCharacteristicByGroupId(characteristicGroupId);
    }

    private static List<CharacteristicValueDAOObject> getCharacteristicValuesByProductId(long productId) {
        return DAOFactory
                .getDAOFactory()
                .getCharacteristicValueDAO()
                .getCharacteristicValuesByProductId(productId);
    }
}
