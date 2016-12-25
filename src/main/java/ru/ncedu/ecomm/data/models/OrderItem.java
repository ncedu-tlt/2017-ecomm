package ru.ncedu.ecomm.data.models;


public class OrderItem {
    private long productId;
    private long salesOrderId;
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(long productId,
                     long salesOrderId,
                     int quantity) {
        this.productId = productId;
        this.salesOrderId = salesOrderId;
        this.quantity = quantity;
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
}
