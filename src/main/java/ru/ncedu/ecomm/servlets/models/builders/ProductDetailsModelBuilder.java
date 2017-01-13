package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.data.models.Review;
import ru.ncedu.ecomm.servlets.models.CharacteristicGroupModel;
import ru.ncedu.ecomm.servlets.models.Images;
import ru.ncedu.ecomm.servlets.models.ProductDetailsModel;

import java.util.List;

public class ProductDetailsModelBuilder {

    private int rating;
    private long id;
    private long price;
    private long discount;
    private String name;
    private String description;

    private List<CharacteristicGroupModel> characteristicGroupModels;
    private List<Images> productImages;

    public ProductDetailsModelBuilder() {
    }


    public ProductDetailsModelBuilder setRating(int rating) {
        this.rating = rating;

        return this;
    }

    public ProductDetailsModelBuilder setId(long id) {
        this.id = id;

        return this;
    }

    public ProductDetailsModelBuilder setPrice(long price) {
        this.price = price;

        return this;
    }

    public ProductDetailsModelBuilder setDiscount(long discount) {
        this.discount = discount;

        return this;
    }

    public ProductDetailsModelBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public ProductDetailsModelBuilder setDescription(String description) {
        this.description = description;

        return this;
    }

    public ProductDetailsModelBuilder setCharacteristicGroupModels(List<CharacteristicGroupModel> characteristicGroupModels) {
        this.characteristicGroupModels = characteristicGroupModels;

        return this;
    }

    public ProductDetailsModelBuilder setProductImages(List<Images> productImages) {
        this.productImages = productImages;

        return this;
    }

    public ProductDetailsModel build(){
        return new ProductDetailsModel(
                rating,
                id,
                price,
                discount,
                name,
                description,
                characteristicGroupModels,
                productImages
        );
    }
}

