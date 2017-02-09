package ru.ncedu.ecomm.servlets.models;

public class OrderItemViewModel {

    private long productId;
    private long salesOrderId;
    private int quantity;
    private String name;
    private long price;
    private String imgUrl;

    public OrderItemViewModel() {
    }

    public OrderItemViewModel(long productId, long salesOrderId, int quantity, String name, long price, String imgUrl) {
        this.productId = productId;
        this.salesOrderId = salesOrderId;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
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

}
