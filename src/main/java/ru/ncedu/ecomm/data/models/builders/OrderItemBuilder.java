package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.OrderItem;

public class OrderItemBuilder {
    private long productId;
    private long salesOrederId;
    private int quantity;

    public OrderItemBuilder(){

    }

    public OrderItemBuilder setProductId(long productId) {
        this.productId = productId;

        return this;
    }

    public OrderItemBuilder setSalesOrederId(long salesOrederId) {
        this.salesOrederId = salesOrederId;

        return this;
    }

    public OrderItemBuilder setQuantity(int quantity) {
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
