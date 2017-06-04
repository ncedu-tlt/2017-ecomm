package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.OrderStatusDAOObject;

import java.util.List;

public interface OrderSratusDAO {
    List<OrderStatusDAOObject> getOrdersStatus();

    OrderStatusDAOObject getOrdersStatusById(long OrderStatusId);
}
