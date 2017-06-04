package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.OrderItem;
import ru.ncedu.ecomm.data.models.dao.OrderStatusDAOObject;
import ru.ncedu.ecomm.data.models.dao.SalesOrder;
import ru.ncedu.ecomm.data.models.dao.SalesOrderDAOObject;
import ru.ncedu.ecomm.data.models.dto.OrderDTOObject;

import java.sql.SQLException;
import java.util.List;

public interface SalesOrdersDAO {
    List<SalesOrderDAOObject> getSalesOrders();

    SalesOrderDAOObject getSalesOrderById(long id);

    SalesOrderDAOObject getSalesOrderByOrderStatusId(long statusId, long userId);

    List<SalesOrderDAOObject> getSalesOrderByOrderUserId(long userId);

    List<SalesOrderDAOObject> getSalesOrderByOrderStatusId(long statusId);

    SalesOrderDAOObject addSalesOrder(SalesOrderDAOObject salesOrder);

    SalesOrderDAOObject updateSalesOrder(SalesOrderDAOObject salesOrder);

    void deleteSalesOrder(SalesOrderDAOObject salesOrder);

    List<SalesOrder> getSalesOrderToOrderHistory(long userId);

    List<OrderItem> getOrderItemsToSalesOrder(long salesOrderId) throws SQLException;

    List<OrderDTOObject> getSalesOrdersForManagement();

    OrderDTOObject getSalesOrderIdForManagement(long salesOrderId);

    OrderDTOObject updateSalesOrderForManagement(OrderDTOObject salesOrder);

    void deleteSalesOrderForManagement(long salesOrderId);
}
