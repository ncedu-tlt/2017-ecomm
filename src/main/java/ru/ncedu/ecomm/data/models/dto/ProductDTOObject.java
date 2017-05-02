package ru.ncedu.ecomm.data.models.dto;

public class ProductDTOObject {
    private long productId;
    private String name;
    private String description;
    private long price;

    public ProductDTOObject() {
    }

    public ProductDTOObject(long productId,
                            String name,
                            String description,
                            long price) {

        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return productId;
    }

    public void setId(long productId) {
        this.productId = productId;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}

