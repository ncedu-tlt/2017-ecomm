package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.OrderItem;

import java.util.List;

public interface OrderItemsDAO {
    List<OrderItem> getOrderItems();

    OrderItem addOrderItem(OrderItem orderItem);

    OrderItem updateOrderItem(OrderItem orderItem);

    void deleteOrderItem(OrderItem orderItem);
}
