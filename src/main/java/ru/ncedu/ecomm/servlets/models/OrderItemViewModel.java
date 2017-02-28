package ru.ncedu.ecomm.servlets.models;

public class OrderItemViewModel {

    private long productId;
    private long salesOrderId;
    private int quantity;
    private long standardPrice;
    private String name;
    private long price;
    private String imgUrl;
    private long discount;

    public OrderItemViewModel() {
    }

    public OrderItemViewModel(long productId, long salesOrderId, int quantity, long standardPrice, String name, long price, String imgUrl, long discount) {
        this.productId = productId;
        this.salesOrderId = salesOrderId;
        this.quantity = quantity;
        this.standardPrice = standardPrice;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.discount = discount;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(long standardPrice) {
        this.standardPrice = standardPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }
}
