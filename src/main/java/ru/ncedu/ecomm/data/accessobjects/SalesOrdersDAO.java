package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.SalesOrder;
import ru.ncedu.ecomm.servlets.models.OrderItemViewModel;
import ru.ncedu.ecomm.servlets.models.SalesOrderViewModel;

import java.sql.SQLException;
import java.util.List;

public interface SalesOrdersDAO {
    List<SalesOrder> getSalesOrders();

    SalesOrder getSalesOrderById(long id);

    SalesOrder getSalesOrderByOrderStatusId(long statusId, long userId);

    List<SalesOrder> getSalesOrderByOrderUserId(long userId);

    List<SalesOrder> getSalesOrderByOrderStatusId(long statusId);

    SalesOrder addSalesOrder(SalesOrder salesOrder);

    SalesOrder updateSalesOrder(SalesOrder salesOrder);

    void deleteSalesOrder(SalesOrder salesOrder);


    List<SalesOrderViewModel> getSalesOrderToOrderHistory(long userId);

    List<OrderItemViewModel> getOrderItemsToSalesOrder(long salesOrderId) throws SQLException;
}
