package ru.ncedu.ecomm.data.accessobjects.impl;

import ru.ncedu.ecomm.data.accessobjects.SalesOrdersDAO;
import ru.ncedu.ecomm.data.models.SalesOrder;

import java.util.ArrayList;
import java.util.List;

public class PostgresSalesOrderDAO implements SalesOrdersDAO {
    @Override
    public List<SalesOrder> getSalesOrders() {
        List<SalesOrder> salesOrders = new ArrayList<>();
        return null;
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
