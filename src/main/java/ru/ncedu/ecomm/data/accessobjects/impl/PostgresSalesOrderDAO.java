package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.SalesOrdersDAO;
import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.data.models.builders.SalesOrderBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresSalesOrderDAO implements SalesOrdersDAO {
    @Override
    public List<SalesOrder> getSalesOrders() {
        List<SalesOrder> salesOrders = new ArrayList<>();

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
                SalesOrder salesOrder = new SalesOrderBuilder()
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setLimit(resultSet.getBigDecimal("limit"))
                        .setOrderStatus(resultSet.getLong("order_status_id"))
                        .build();

                salesOrders.add(salesOrder);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salesOrders;
    }

    @Override
    public SalesOrder getSalesOrderById(long id) {
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
                return new SalesOrderBuilder()
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setLimit(resultSet.getBigDecimal("limit"))
                        .setOrderStatus(resultSet.getLong("order_status_id"))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<SalesOrder> getSalesOrderByOrderStatusId(long statusId, long userId) {
        List<SalesOrder> salesOrders = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT\n" +
                        " sales_order_id, \n" +
                        " creation_date," +
                        " \"limit\"\n" +
                        "FROM sales_orders\n" +
                        "WHERE order_status_id = ?\n" +
                        "AND user_id = ?\n" +
                        "ORDER BY creation_date DESC")){
            statement.setLong(1, statusId);
            statement.setLong(2, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                SalesOrder salesOrder = new SalesOrderBuilder()
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setLimit(resultSet.getBigDecimal("limit"))
                        .build();
                salesOrders.add(salesOrder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salesOrders;
    }

    @Override
    public SalesOrder getSalesOrderByUserId(long userId) {
        SalesOrder salesOrdersByUserId = new SalesOrder();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT\n" +
                             "  sales_order_id,\n" +
                             "  user_id,\n" +
                             "  creation_date,\n" +
                             "  \"limit\",\n" +
                             "  order_status_id\n" +
                             "FROM public.sales_orders\n" +
                             "WHERE user_id = ?")) {

            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                SalesOrder salesOrder = new SalesOrderBuilder()
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setLimit(resultSet.getBigDecimal("limit"))
                        .setOrderStatus(resultSet.getLong("order_status_id"))
                        .build();
                salesOrdersByUserId = salesOrder;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salesOrdersByUserId;
    }


    @Override
    public SalesOrder addSalesOrder(SalesOrder salesOrder) {
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
//            statement.setBigDecimal(3, salesOrder.getLimit());
            statement.setLong(3, salesOrder.getOrderStatusId());
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                salesOrder.setSalesOrderId(statement.getResultSet().getLong(1));
            }

            return salesOrder;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SalesOrder updateSalesOrder(SalesOrder salesOrder) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE public.sales_orders\n" +
                             "SET user_id       = ?,\n" +
                             "  creation_date   = ?,\n" +
                             "  \"limit\"         = ?,\n" +
                             "  order_status_id = ?,\n" +
                             " total_price = ?\n" +
                             "WHERE sales_order_id = ?"
             )) {

            statement.setLong(1, salesOrder.getUserId());
            statement.setDate(2, salesOrder.getCreationDate());
            statement.setBigDecimal(3, salesOrder.getLimit());
            statement.setLong(4, salesOrder.getOrderStatusId());
            statement.setLong(5, salesOrder.getTotalPrice());
            statement.setLong(6, salesOrder.getSalesOrderId());
            statement.execute();

            return salesOrder;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSalesOrder(SalesOrder salesOrder) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM public.sales_orders\n" +
                             "WHERE sales_order_id = ?")) {

            statement.setLong(1, salesOrder.getSalesOrderId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
