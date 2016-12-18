package ru.ncedu.ecomm.data.models;

import java.util.List;

public class OrderStatus {
    private long orderStatusId;
    private String name;

    public OrderStatus() {
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
