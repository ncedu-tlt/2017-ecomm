package ru.ncedu.ecomm.data.models.dao;


public class OrderStatusDAOObject {
    private long orderStatusId;
    private String name;

    public OrderStatusDAOObject(long orderStatusId,
                                String name) {
        this.orderStatusId = orderStatusId;
        this.name = name;
    }

    public OrderStatusDAOObject() {
    }

    public long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
