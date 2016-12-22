package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.SalesOrdersDAO;
import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.data.models.builders.SalesOrderBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresSalesOrderDAO implements SalesOrdersDAO {
    @Override
    public List<SalesOrder> getSalesOrders() {
        List<SalesOrder> salesOrders = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT sales_order_id," +
                    "user_id, creation_date, limit, order_status_id " +
                    "FROM sales_orders");
            while (resultSet.next()){
                SalesOrder salesOrder = new SalesOrderBuilder()
                        .setSalesOrderId(resultSet.getLong("sales_order_id"))
                        .setUserId(resultSet.getLong("user_id"))
                        .setCreationDate(resultSet.getDate("creation_date"))
                        .setLimit(resultSet.getLong("limit"))
                        .setOrederStatusId(resultSet.getLong("order_status_id"))
                        .build();

                salesOrders.add(salesOrder);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salesOrders;
    }

    @Override
    public SalesOrder addSalesOrder(SalesOrder salesOrder) {
        return null;
    }

    @Override
    public SalesOrder updateSalesOrder(SalesOrder salesOrder) {
        return null;
    }

    @Override
    public void deleteSalesOrder(SalesOrder salesOrder) {

    }
}
