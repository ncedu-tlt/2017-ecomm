package ru.ncedu.ecomm.data.accessobjects.impl;

import org.apache.log4j.Logger;
import ru.ncedu.ecomm.data.accessobjects.OrderItemsDAO;
import ru.ncedu.ecomm.data.models.dao.OrderItemDAOObject;
import ru.ncedu.ecomm.data.models.dao.builders.OrderItemDAOObjectBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresOrderItemsDAO implements OrderItemsDAO {
    private static final Logger LOG  = Logger.getLogger(PostgresOrderItemsDAO.class);

    @Override
    public List<OrderItemDAOObject> getOrderItems() {

        List<OrderItemDAOObject> orderItems = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  product_id,\n" +
                            "  sales_order_id,\n" +
                            "  quantity,\n" +
                            "  standard_price\n" +
                            "FROM public.order_items");

            while (resultSet.next()) {
                OrderItemDAOObject orderItem = new OrderItemDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setSalesOrederId(resultSet.getLong("sales_order_id"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .setStandardPrice(resultSet.getLong("standard_price"))
                        .build();

                orderItems.add(orderItem);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return orderItems;
    }

    @Override
    public List<OrderItemDAOObject> getOrderItemsBySalesOrderId(long salesOrderId) {
        List <OrderItemDAOObject> orderItemsBySalesOrderId = new ArrayList<>();
        try(Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
               "SELECT\n"+
                       " product_id,\n" +
                       " sales_order_id,\n" +
                       " quantity,\n" +
                       " standard_price\n" +
                       "FROM order_items\n" +
                       "WHERE sales_order_id = ?\n")) {
            preparedStatement.setLong(1, salesOrderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                OrderItemDAOObject orderItem = new OrderItemDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setSalesOrederId(resultSet.getLong("sales_order_id"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .setStandardPrice(resultSet.getLong("standard_price"))
                        .build();
                orderItemsBySalesOrderId.add(orderItem);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return orderItemsBySalesOrderId;
    }

    @Override
    public OrderItemDAOObject addOrderItem(OrderItemDAOObject orderItem) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.order_items\n" +
                             "(product_id, sales_order_id, quantity, standard_price)\n" +
                             "VALUES (?, ?, ?, ?)"
             )) {
            statement.setLong(1, orderItem.getProductId());
            statement.setLong(2, orderItem.getSalesOrderId());
            statement.setInt(3, orderItem.getQuantity());
            statement.setLong(4, orderItem.getStandardPrice());
            statement.execute();

            LOG.info(null);
            return orderItem;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderItemDAOObject updateOrderItem(OrderItemDAOObject orderItem) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.order_items\n" +
                             "SET quantity = ?\n," +
                             "    standard_price = ?\n" +
                             "WHERE product_id = ?\n" +
                             "      AND sales_order_id = ?"
             )) {

            statement.setInt(1, orderItem.getQuantity());
            statement.setLong(2, orderItem.getStandardPrice());
            statement.setLong(3, orderItem.getProductId());
            statement.setLong(4, orderItem.getSalesOrderId());
            statement.execute();

            LOG.info(null);
            return orderItem;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderItemDAOObject getOrderItem(long productId, long salesOrderId) throws SQLException {
        OrderItemDAOObject orderItem = new OrderItemDAOObject();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT\n" +
                            "  product_id,\n" +
                            "  sales_order_id,\n" +
                            "  quantity,\n" +
                            "  standard_price\n"+
                            "FROM public.order_items \n" +
                            "WHERE product_id = ? \n AND " +
                            "sales_order_id = ?")){
            preparedStatement.setLong(1, productId);
            preparedStatement.setLong(2, salesOrderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderItem = new OrderItemDAOObjectBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setSalesOrederId(resultSet.getLong("sales_order_id"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .setStandardPrice(resultSet.getLong("standard_price"))
                        .build();
            }

            LOG.info(null);
            return orderItem;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderItem(OrderItemDAOObject orderItem) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.order_items\n" +
                             "WHERE product_id = ?\n" +
                             "      AND sales_order_id = ?"
             )) {
            statement.setLong(1, orderItem.getProductId());
            statement.setLong(2, orderItem.getSalesOrderId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public long getQuantityBySalesOrderId(long salesOrderId) {
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

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return quantityProducts;
    }
}
