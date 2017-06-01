package ru.ncedu.ecomm.data.models.dto;

import ru.ncedu.ecomm.data.models.dao.OrderItem;
import ru.ncedu.ecomm.data.models.dao.OrderStatusDAOObject;

import java.sql.Date;
import java.util.List;

public class OrderDTOObject {
    private long salesOrderId;
    private String userName;
    private Date creationDate;
    private List<OrderItem> orderItems;
    private long totalAmount;
    private OrderStatusDAOObject orderStatus;

    public OrderDTOObject() {
    }

    public OrderDTOObject(String userName, long salesOrderId, Date creationDate, List<OrderItem> orderItems, long totalAmount, OrderStatusDAOObject orderStatus) {
        this.userName = userName;
        this.salesOrderId = salesOrderId;
        this.creationDate = creationDate;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatusDAOObject getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusDAOObject orderStatus) {
        this.orderStatus = orderStatus;
    }

}
