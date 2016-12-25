package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.OrderItem;

public class OrderItamBuilder {
    private long productId;
    private long salesOrederId;
    private int quantity;

    public OrderItamBuilder(){

    }

    public OrderItamBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public OrderItamBuilder setSalesOrederId(long salesOrederId) {
        this.salesOrederId = salesOrederId;

        return this;
    }

    public OrderItamBuilder setQuantity(int quantity) {
        this.quantity = quantity;

        return this;
    }

    public OrderItem build(){
        return new OrderItem(
                productId,
                salesOrederId,
                quantity
        );
    }
}
