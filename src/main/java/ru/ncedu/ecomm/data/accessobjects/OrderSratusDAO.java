package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.OrderStatus;

import java.util.List;

public interface OrderSratusDAO {
    List<OrderStatus> getOrdersStatus();

    OrderStatus addOrderStatus(OrderStatus orderStatus);

    OrderStatus updateOrderStarus(OrderStatus orderStatus);

    void deleteOrderStatus(OrderStatus orderStatus);

}
