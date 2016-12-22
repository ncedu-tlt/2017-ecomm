package ru.ncedu.ecomm.data.models;

import java.math.BigDecimal;
import java.sql.Date;

public class SalesOrder {


    private long salesOrderId;
    private long userId;
    private Date creationDate;
    private BigDecimal limit;
    private long orderStatusId;

    public SalesOrder() {
    }

    public SalesOrder(long salesOrderId,
                      long userId,
                      Date creationDate,
                      BigDecimal limit,
                      long orderStatusId) {

        this.salesOrderId = salesOrderId;
        this.userId = userId;
        this.creationDate = creationDate;
        this.limit = limit;
        this.orderStatusId = orderStatusId;
    }

    public long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }
}
