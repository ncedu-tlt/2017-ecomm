package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.OrderItemsDAO;
import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresOrderItemsDAO implements OrderItemsDAO{
    @Override
    public List<OrderItem> getOrderItems() {

        List<OrderItem> orderItems = new ArrayList<>();

        try(Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT product_id, sales_order_id, quantity FROM public.order_items");

            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(resultSet.getLong("product_id"));
                orderItem.setSalesOrderId(resultSet.getLong("sales_order_id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));


                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItems;
    }

    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        return null;
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return null;
    }

    @Override
    public void deleteOrderItem(OrderItem orderItem) {

    }
}
