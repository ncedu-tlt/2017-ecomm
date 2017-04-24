package ru.ncedu.ecomm.data.models.dao.builders;

import ru.ncedu.ecomm.data.models.dao.OrderItemDAOObject;

public class OrderItemDAOObjectBuilder {
    private long productId;
    private long salesOrederId;
    private int quantity;
    private long standardPrice;

    public OrderItemDAOObjectBuilder(){

    }

    public OrderItemDAOObjectBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public OrderItemDAOObjectBuilder setSalesOrederId(long salesOrederId) {
        this.salesOrederId = salesOrederId;

        return this;
    }

    public OrderItemDAOObjectBuilder setQuantity(int quantity) {
        this.quantity = quantity;

        return this;
    }

    public OrderItemDAOObjectBuilder setStandardPrice(long standardPrice) {
        this.standardPrice = standardPrice;
        return this;
    }

    public OrderItemDAOObject build(){
        return new OrderItemDAOObject(
                productId,
                salesOrederId,
                quantity,
                standardPrice
        );
    }
}
