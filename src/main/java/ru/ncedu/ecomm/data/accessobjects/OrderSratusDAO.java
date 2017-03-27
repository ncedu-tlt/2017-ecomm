package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.OrderStatusDAOObject;

public interface OrderSratusDAO {
    OrderStatusDAOObject getOrdersStatusById(long OrderStatusId);
}
