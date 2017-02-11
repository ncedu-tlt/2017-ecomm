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
                    "SELECT\n" +
                            "  product_id,\n" +
                            "  sales_order_id,\n" +
                            "  quantity\n" +
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
    public List<OrderItem> getOrderItemsBySalesOrderId(long salesOrderId) {
        List <OrderItem> orderItemsBySalesOrderId = new ArrayList<>();
        try(Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
               "SELECT\n"+
                       " product_id,\n" +
                       " sales_order_id,\n" +
                       " quantity\n" +
                       "FROM order_items\n" +
                       "WHERE sales_order_id = ?\n")) {
            preparedStatement.setLong(1, salesOrderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                OrderItem orderItem = new OrderItemBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setSalesOrederId(resultSet.getLong("sales_order_id"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .build();
                orderItemsBySalesOrderId.add(orderItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItemsBySalesOrderId;
    }

    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.order_items\n" +
                             "(product_id, sales_order_id, quantity)\n" +
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
                     "UPDATE public.order_items\n" +
                             "SET quantity = ?\n" +
                             "WHERE product_id = ?\n" +
                             "      AND sales_order_id = ?"
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
    public OrderItem getOrderItem(long productId, long salesOrderId) throws SQLException {
        OrderItem orderItem = new OrderItem();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT\n" +
                            "  product_id,\n" +
                            "  sales_order_id,\n" +
                            "  quantity\n" +
                            "FROM public.order_items \n" +
                            "WHERE product_id = ? \n AND " +
                            "sales_order_id = ?")){
            preparedStatement.setLong(1, productId);
            preparedStatement.setLong(2, salesOrderId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderItem = new OrderItemBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setSalesOrederId(resultSet.getLong("sales_order_id"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .build();
            }
            return orderItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderItem(OrderItem orderItem) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.order_items\n" +
                             "WHERE product_id = ?\n" +
                             "      AND sales_order_id = ?"
             )) {
            statement.setLong(1, orderItem.getProductId());
            statement.setLong(2, orderItem.getSalesOrderId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long getProductsBySalesOrderId(long salesOrderId) {
        long quantityProducts = 0;
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT \n" +
                             "SUM(quantity) \n" +
                             "FROM order_items \n" +
                             "WHERE sales_order_id = ?"

             )) {
            statement.setLong(1, salesOrderId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                quantityProducts = resultSet.getLong("sum");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quantityProducts;
    }
}
