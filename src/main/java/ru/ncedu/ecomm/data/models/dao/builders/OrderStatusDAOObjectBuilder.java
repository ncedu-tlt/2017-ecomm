package ru.ncedu.ecomm.data.models.dao.builders;


import ru.ncedu.ecomm.data.models.dao.OrderStatusDAOObject;

public class OrderStatusDAOObjectBuilder {
    private long orderStatusId;
    private String name;

    public OrderStatusDAOObjectBuilder(){

    }

    public OrderStatusDAOObjectBuilder setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;

        return this;
    }

    public OrderStatusDAOObjectBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public OrderStatusDAOObject build(){
        return new OrderStatusDAOObject(
                orderStatusId,
                name
        );
    }
}
