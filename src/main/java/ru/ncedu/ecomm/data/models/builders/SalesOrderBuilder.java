package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.data.models.SalesOrder;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class SalesOrderBuilder {

    private long userId;
    private long salesOrderId;
    private Date creationDate;
    private BigDecimal limit;
    private List<OrderItem> orderItems;
    private long totalAmount;
    private String statusName;

    public SalesOrderBuilder(){}

    public SalesOrderBuilder setUserId(long userId){
        this.userId = userId;
        return this;
    }

    public SalesOrderBuilder setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
        return this;
    }

    public SalesOrderBuilder setLimit(BigDecimal limit) {
        this.limit = limit;
        return this;
    }

    public SalesOrderBuilder setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public SalesOrderBuilder setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public SalesOrderBuilder setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public SalesOrderBuilder setStatusName(String statusName) {
        this.statusName = statusName;
        return this;
    }

    public SalesOrder build(){
        return new SalesOrder(userId, salesOrderId, creationDate, limit, orderItems, totalAmount, statusName);
    }
}
