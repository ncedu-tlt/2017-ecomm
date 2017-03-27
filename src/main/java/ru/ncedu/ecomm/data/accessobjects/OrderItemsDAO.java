package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.OrderItemDAOObject;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemsDAO {
    List<OrderItemDAOObject> getOrderItems();

    List<OrderItemDAOObject> getOrderItemsBySalesOrderId(long salesOrderId);

    OrderItemDAOObject addOrderItem(OrderItemDAOObject orderItem);

    OrderItemDAOObject updateOrderItem(OrderItemDAOObject orderItem);

    OrderItemDAOObject getOrderItem(long productId, long salesOrderId) throws SQLException;

    void deleteOrderItem(OrderItemDAOObject orderItem);

    long getQuantityBySalesOrderId(long salesOrderId);

}
