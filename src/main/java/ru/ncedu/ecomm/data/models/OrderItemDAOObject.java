package ru.ncedu.ecomm.data.models;


public class OrderItemDAOObject {
    private long productId;
    private long salesOrderId;
    private int quantity;
    private long standardPrice;

    public OrderItemDAOObject() {
    }

    public OrderItemDAOObject(long productId,
                              long salesOrderId,
                              int quantity,
                              long standardPrice) {
        this.productId = productId;
        this.salesOrderId = salesOrderId;
        this.quantity = quantity;
        this.standardPrice = standardPrice;
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
}
