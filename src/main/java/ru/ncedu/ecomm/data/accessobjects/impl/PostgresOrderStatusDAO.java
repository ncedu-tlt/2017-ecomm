package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.OrderSratusDAO;
import ru.ncedu.ecomm.data.models.OrderStatus;
import ru.ncedu.ecomm.data.models.builders.OrderStatusBuilder;
import ru.ncedu.ecomm.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class PostgresOrderStatusDAO implements OrderSratusDAO{
    private static final Logger LOG  = Logger.getLogger(PostgresOrderStatusDAO.class);

    @Override
    public OrderStatus getOrdersStatusById(long oderStatusId) {
        try(Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT\n" +
                            "  order_status_id,\n" +
                            "  name\n" +
                            "FROM order_statuses\n" +
                            "WHERE order_status_id = ?"
            )) {
            statement.setLong(1, oderStatusId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                LOG.info(null);
                return new OrderStatusBuilder()
                        .setName(resultSet.getString("name"))
                        .setOrderStatusId(resultSet.getLong("order_status_id"))
                        .build();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
