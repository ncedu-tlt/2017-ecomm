package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.OrderItemsDAO;
import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.data.models.builders.OrderItemBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresOrderItemsDAO implements OrderItemsDAO {
    @Override
    public List<OrderItem> getOrderItems() {

        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT product_id, " +
                            "sales_order_id, " +
                            "quantity " +
                            "FROM public.order_items");

            while (resultSet.next()) {
                OrderItem orderItem = new OrderItemBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setSalesOrederId(resultSet.getLong("sales_order_id"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .build();

                orderItems.add(orderItem);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItems;
    }

    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO  public.order_items " +
                             "(product_id, sales_order_id, quantity) " +
                             "VALUES (?, ?, ?)"
             )) {
            statement.setLong(1, orderItem.getProductId());
            statement.setLong(2, orderItem.getSalesOrderId());
            statement.setInt(3, orderItem.getQuantity());
            statement.execute();

            return orderItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.order_items " +
                             "SET quantity = ? " +
                             "WHERE product_id = ? " +
                             "AND sales_order_id = ?"
             )) {

            statement.setInt(1, orderItem.getQuantity());
            statement.setLong(2, orderItem.getProductId());
            statement.setLong(3, orderItem.getSalesOrderId());
            statement.execute();

            return orderItem;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderItem(OrderItem orderItem) {
        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM public.order_items " +
                            "WHERE product_id = ? " +
                            "AND sales_order_id = ?"
            )) {
            statement.setLong(1, orderItem.getProductId());
            statement.setLong(2, orderItem.getSalesOrderId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
