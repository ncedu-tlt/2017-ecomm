package ru.ncedu.ecomm.data.accessobjects;

import ru.ncedu.ecomm.data.models.OrderStatus;

import java.util.List;

public interface OrderSratusDAO {
    OrderStatus getOrdersStatusById(long OrderStatusId);
}
