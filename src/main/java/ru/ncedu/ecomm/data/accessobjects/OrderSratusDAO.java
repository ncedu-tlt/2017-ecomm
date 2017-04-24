package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.dao.OrderStatusDAOObject;

public interface OrderSratusDAO {
    OrderStatusDAOObject getOrdersStatusById(long OrderStatusId);
}
