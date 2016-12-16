package ru.ncedu.ecomm.data.models;


public class Product {
    private long productId;
    private long categoryId;
    private String name;
    private String description;
    private long discountId;
    private long price;

    public Product() {
    }

    public Product(long productId, long categoryId, String name, String description, long discountId, long price) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.discountId = discountId;
        this.price = price;
    }

    public Product(long productId) {
        this.productId = productId;
    }

    public Product(long productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public Product(long productId, String name, long categoryId) {
        this.productId = productId;
        this.name = name;
        this.categoryId = categoryId;
    }

    public Product(long categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public Product(long productId, String name, long categoryId, String description) {
        this.productId = productId;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
    }

    public Product(Product product) {
        this(product.getId(), product.getName(),
                product.getCategoryId(), product.getDescription());
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
