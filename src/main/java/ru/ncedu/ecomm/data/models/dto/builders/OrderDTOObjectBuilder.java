package ru.ncedu.ecomm.data.models.dto.builders;

import ru.ncedu.ecomm.data.models.dao.OrderItem;
import ru.ncedu.ecomm.data.models.dao.OrderStatusDAOObject;
import ru.ncedu.ecomm.data.models.dto.OrderDTOObject;

import java.sql.Date;
import java.util.List;

public class OrderDTOObjectBuilder {
    private long salesOrderId;
    private String userName;
    private Date creationDate;
    private List<OrderItem> orderItems;
    private long totalAmount;
    private OrderStatusDAOObject orderStatus;

    public OrderDTOObjectBuilder() {
    }

    public OrderDTOObjectBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public OrderDTOObjectBuilder setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
        return this;
    }

    public OrderDTOObjectBuilder setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public OrderDTOObjectBuilder setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public OrderDTOObjectBuilder setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public OrderDTOObjectBuilder setOrderStatus(OrderStatusDAOObject orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public OrderDTOObject build() {
        return new OrderDTOObject(userName, salesOrderId, creationDate, orderItems, totalAmount, orderStatus);
    }
}
