package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.OrderSratusDAO;
import ru.ncedu.ecomm.data.models.OrderStatus;

import java.util.List;

public class PostgresOrderStatusDAO implements OrderSratusDAO{
    @Override
    public List<OrderStatus> getOrdersStatus() {
        return null;
    }

    @Override
    public OrderStatus addOrderStatus(OrderStatus orderStatus) {
        return null;
    }

    @Override
    public OrderStatus updateOrderStarus(OrderStatus orderStatus) {
        return null;
    }

    @Override
    public void deleteOrderStatus(OrderStatus orderStatus) {

    }
}
