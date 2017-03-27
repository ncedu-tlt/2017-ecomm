package ru.ncedu.ecomm.data.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class SalesOrder {

    private long userId;
    private long salesOrderId;
    private Date creationDate;
    private BigDecimal limit;
    private List<OrderItem> orderItems;
    private long totalAmount;
    private String statusName;

    public SalesOrder(){}

    public SalesOrder(long userId, long salesOrderId, Date creationDate, BigDecimal limit, List<OrderItem> orderItems, long totalAmount, String statusName) {
        this.userId = userId;
        this.salesOrderId = salesOrderId;
        this.creationDate = creationDate;
        this.limit = limit;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.statusName = statusName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
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

    public String getStatusName() {
        return statusName;
    }
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
