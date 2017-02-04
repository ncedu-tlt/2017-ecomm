package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.SalesOrder;

import java.util.List;

public interface SalesOrdersDAO {
    List<SalesOrder> getSalesOrders();

    SalesOrder getSalesOrderById(long id);

    SalesOrder getSalesOrderByUserId(long userId);

    SalesOrder addSalesOrder(SalesOrder salesOrder);

    SalesOrder updateSalesOrder(SalesOrder salesOrder);

    void deleteSalesOrder(SalesOrder salesOrder);
}
