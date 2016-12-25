package ru.ncedu.ecomm.data.models.builders;


import ru.ncedu.ecomm.data.models.OrderStatus;

public class OrderStatusBuilder {
    private long orderStatusId;
    private String name;

    public OrderStatusBuilder(){

    }

    public OrderStatusBuilder setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;

        return this;
    }

    public OrderStatusBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public OrderStatus build(){
        return new OrderStatus(
                orderStatusId,
                name
        );
    }
}
