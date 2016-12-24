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
                    "SELECT sales_order_id, " +
                            "user_id, " +
                            "creation_date, " +
                            "\"limit\", " +
                            "order_status_id " +
                            "FROM public.sales_orders");
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
                     "SELECT sales_order_id, " +
                             "user_id, " +
                             "creation_date, " +
                             "\"limit\", " +
                             "order_status_id " +
                             "FROM public.sales_orders " +
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
    public SalesOrder addSalesOrder(SalesOrder salesOrder) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO  public.sales_orders " +
                             "(user_id, " +
                             "creation_date, " +
                             "\"limit\", " +
                             "order_status_id) " +
                             "VALUES (?, ?, ?, ?) " +
                             "RETURNING sales_order_id")) {

            statement.setLong(1, salesOrder.getUserId());
            statement.setDate(2, salesOrder.getCreationDate());
            statement.setBigDecimal(3, salesOrder.getLimit());
            statement.setLong(4, salesOrder.getSalesOrderId());
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
                     "UPDATE public.sales_orders" +
                             " SET user_id = ?, " +
                             "creation_date = ?, " +
                             "\"limit\" = ?, " +
                             "order_status_id = ? " +
                             "WHERE sales_order_id = ?")) {
            statement.setLong(1, salesOrder.getUserId());
            statement.setDate(2, salesOrder.getCreationDate());
            statement.setBigDecimal(3, salesOrder.getLimit());
            statement.setLong(4, salesOrder.getOrderStatusId());
            statement.setLong(5, salesOrder.getSalesOrderId());
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
                     "DELETE FROM public.sales_orders " +
                             "WHERE sales_order_id = ?")) {

            statement.setLong(1, salesOrder.getSalesOrderId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
