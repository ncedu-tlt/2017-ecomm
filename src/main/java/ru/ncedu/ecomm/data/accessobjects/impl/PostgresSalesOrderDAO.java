package ru.ncedu.ecomm.data.accessobjects.impl;

import org.apache.log4j.Logger;
import ru.ncedu.ecomm.data.accessobjects.SalesOrdersDAO;
import ru.ncedu.ecomm.data.models.SalesOrderDAOObject;
import ru.ncedu.ecomm.data.models.builders.SalesOrderDAOObjectBuilder;
import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.data.models.builders.OrderItemBuilder;
import ru.ncedu.ecomm.data.models.builders.SalesOrderBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresSalesOrderDAO implements SalesOrdersDAO {
    private static final Logger LOG  = Logger.getLogger(PostgresSalesOrderDAO.class);
    @Override
    public List<SalesOrderDAOObject> getSalesOrders() {
        List<SalesOrderDAOObject> salesOrders = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(
                    "SELECT\n" +
                            "  sales_order_id,\n" +
                            "  user_id,\n" +
                            "  creation_date,\n" +
                            "\"limit\",\n" +
                            "order_status_id\n" +
                            "FROM PUBLIC.sales_orders");
            while (resultSet.next()) {
                SalesOrderDAOObject salesOrder = new SalesOrderDAOObjectBuilder()
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setLimit(resultSet.getBigDecimal("limit"))
                        .setOrderStatus(resultSet.getLong("order_status_id"))
                        .build();

                salesOrders.add(salesOrder);
            }

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return salesOrders;
    }

    @Override
    public SalesOrderDAOObject getSalesOrderById(long id) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  sales_order_id,\n" +
                             "  user_id,\n" +
                             "  creation_date,\n" +
                             "  \"limit\",\n" +
                             "  order_status_id\n" +
                             "FROM public.sales_orders\n" +
                             "WHERE sales_order_id = ?")) {

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LOG.info(null);
                return new SalesOrderDAOObjectBuilder()
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setLimit(resultSet.getBigDecimal("limit"))
                        .setOrderStatus(resultSet.getLong("order_status_id"))
                        .build();
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public SalesOrderDAOObject getSalesOrderByOrderStatusId(long statusId, long userId) {

        try (Connection connection = DBUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT\n" +
                        "user_id, \n" +
                        " sales_order_id, \n" +
                        " creation_date," +
                        " \"limit\",\n" +
                        "order_status_id\n" +
                        "FROM sales_orders\n" +
                        "WHERE order_status_id = ?\n" +
                        "AND user_id = ?\n" +
                        "ORDER BY creation_date DESC")){
            statement.setLong(1, statusId);
            statement.setLong(2, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new SalesOrderDAOObjectBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setLimit(resultSet.getBigDecimal("limit"))
                        .setOrderStatus(resultSet.getLong("order_status_id"))
                        .build();
            }
            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<SalesOrderDAOObject> getSalesOrderByOrderUserId(long userId) {
        List<SalesOrderDAOObject> salesOrders = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "user_id, \n" +
                             " sales_order_id, \n" +
                             " creation_date," +
                             " \"limit\",\n" +
                             "order_status_id\n" +
                             "FROM sales_orders\n" +
                             "WHERE user_id = ?\n" +
                             "ORDER BY creation_date DESC")){
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                SalesOrderDAOObject salesOrder = new SalesOrderDAOObjectBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setLimit(resultSet.getBigDecimal("limit"))
                        .setOrderStatus(resultSet.getLong("order_status_id"))
                        .build();
                salesOrders.add(salesOrder);
            }
            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return salesOrders;
    }

    @Override
    public List<SalesOrderDAOObject> getSalesOrderByOrderStatusId(long statusId) {
        List<SalesOrderDAOObject> salesOrders = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "user_id, \n" +
                             " sales_order_id, \n" +
                             " creation_date," +
                             " \"limit\"\n" +
                             "FROM sales_orders\n" +
                             "WHERE order_status_id = ?\n" +
                             "ORDER BY creation_date DESC")){
            statement.setLong(1, statusId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                SalesOrderDAOObject salesOrder = new SalesOrderDAOObjectBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setLimit(resultSet.getBigDecimal("limit"))
                        .build();
                salesOrders.add(salesOrder);
            }
            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return salesOrders;
    }

    @Override
    public List<SalesOrder> getSalesOrderToOrderHistory(long userId) {
        List<SalesOrder> salesOrders = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             " user_id,\n" +
                             " sales_orders.sales_order_id,\n" +
                             " creation_date,\n" +
                             " order_statuses.name,\n" +
                             " summ.total\n" +
                             "FROM sales_orders,\n" +
                             " order_statuses, \n " +
                             " (SELECT SUM(order_items.standard_price * order_items.quantity) as total,\n" +
                             " sales_orders.sales_order_id\n " +
                             " FROM order_items,\n " +
                             "sales_orders\n " +
                             " WHERE order_items.sales_order_id = sales_orders.sales_order_id\n" +
                             " GROUP BY  sales_orders.user_id,\n " +
                             "sales_orders.sales_order_id) summ\n" +
                             "WHERE user_id = ?\n" +
                             " AND order_statuses.order_status_id = sales_orders.order_status_id \n" +
                             " AND summ.sales_order_id = sales_orders.sales_order_id\n" +
                             "ORDER BY creation_date DESC")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SalesOrder salesOrder = new SalesOrderBuilder()
                        .setUserId(resultSet.getLong("user_id"))
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setStatusName(resultSet.getString("name"))
                        .setTotalAmount(resultSet.getLong("total"))
                        .build();
                salesOrder.setOrderItems(getOrderItemsToSalesOrder(salesOrder.getSalesOrderId()));

                salesOrders.add(salesOrder);

            }
            LOG.info(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salesOrders;

    }

    @Override
    public List<OrderItem> getOrderItemsToSalesOrder(long salesOrderId) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             " order_items.product_id,\n" +
                             " quantity,\n" +
                             " products.name,\n" +
                             " standard_price,\n" +
                             " discount_id,\n" +
                             " COALESCE(" +
                             "NULLIF(imgUrl.value, " +
                             "'/images/defaultimage/image.png'), " +
                             "'/images/defaultimage/image.png') as image\n" +
                             "FROM order_items,\n" +
                             " products\n" +
                             " LEFT JOIN\n" +
                             "(SELECT characteristic_values.value,\n " +
                             " characteristic_values.product_id\n" +
                             " FROM characteristic_values, characteristics\n" +
                             " WHERE characteristic_values.characteristic_id = characteristics.characteristic_id\n" +
                             "AND characteristics.characteristic_id = 28)  imgUrl\n" +
                             " ON imgUrl.product_id = products.product_id\n" +
                             "WHERE order_items.sales_order_id = ?\n" +
                             " AND order_items.product_id = products.product_id")) {

            statement.setLong(1, salesOrderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItemBuilder()
                        .setProductId(resultSet.getLong("product_id"))
                        .setQuantity(resultSet.getInt("quantity"))
                        .setName(resultSet.getString("name"))
                        .setPrice(resultSet.getLong("standard_price"))
                        .setDiscount(resultSet.getLong("discount_id"))
                        .setImgUrl(resultSet.getString("image"))
                        .build();
                orderItems.add(orderItem);
            }
            LOG.info(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderItems;
    }

    @Override
    public SalesOrderDAOObject addSalesOrder(SalesOrderDAOObject salesOrder) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO public.sales_orders\n" +
                             "(user_id,\n" +
                             " creation_date,\n" +
                             " order_status_id)\n" +
                             "VALUES (?, ?, ?)\n" +
                             "RETURNING sales_order_id")) {

            statement.setLong(1, salesOrder.getUserId());
            statement.setDate(2, salesOrder.getCreationDate());
            statement.setLong(3, salesOrder.getOrderStatusId());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            Long salesOrderId;
            if (resultSet.next()) {
                salesOrderId = statement.getResultSet().getLong(1);
                salesOrder.setSalesOrderId(salesOrderId);
            }

            LOG.info(null);
            return salesOrder;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public SalesOrderDAOObject updateSalesOrder(SalesOrderDAOObject salesOrder) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.sales_orders\n" +
                             "SET user_id       = ?,\n" +
                             "  creation_date   = ?,\n" +
                             "  \"limit\"         = ?,\n" +
                             "  order_status_id = ?\n" +
                             "WHERE sales_order_id = ?"
             )) {

            statement.setLong(1, salesOrder.getUserId());
            statement.setDate(2, salesOrder.getCreationDate());
            statement.setBigDecimal(3, salesOrder.getLimit());
            statement.setLong(4, salesOrder.getOrderStatusId());
            statement.setLong(5, salesOrder.getSalesOrderId());
            statement.execute();

            LOG.info(null);
            return salesOrder;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSalesOrder(SalesOrderDAOObject salesOrder) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.sales_orders\n" +
                             "WHERE sales_order_id = ?")) {

            statement.setLong(1, salesOrder.getSalesOrderId());
            statement.execute();

            LOG.info(null);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
