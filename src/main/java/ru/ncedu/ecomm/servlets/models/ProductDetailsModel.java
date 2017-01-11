package ru.ncedu.ecomm.servlets.models;

import ru.ncedu.ecomm.data.models.CharacteristicGroup;

import java.util.List;

public class ProductDetailsModel {
    private int rating;
    private long id;
    private long price;
    private long discount;
    private String name;
    private String description;


    private List<CharacteristicGroupModel> characteristicGroupModels;
    private List<Images> imagesList;

    public ProductDetailsModel() {
    }

    public ProductDetailsModel(int rating,
                               long id,
                               long price,
                               long discount,
                               String name,
                               String description,
                               List<CharacteristicGroupModel> characteristicGroupModels,
                               List<Images> imagesList) {
        this.rating = rating;
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.name = name;
        this.description = description;
        this.characteristicGroupModels = characteristicGroupModels;
        this.imagesList = imagesList;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public List<CharacteristicGroupModel> getCharacteristicGroupModels() {
        return characteristicGroupModels;
    }

    public void setCharacteristicGroupModels(List<CharacteristicGroupModel> characteristicGroupModels) {
        this.characteristicGroupModels = characteristicGroupModels;
    }

    public List<Images> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<Images> imagesList) {
        this.imagesList = imagesList;
    }
}
