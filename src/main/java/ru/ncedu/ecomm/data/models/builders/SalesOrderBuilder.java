package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.SalesOrder;

import java.util.Date;

public class SalesOrderBuilder {

    private long salesOrderId;
    private long userId;
    private Date creationDate;
    private String limit;
    private long orderStatus;
    public SalesOrderBuilder(){

    }

    SalesOrderBuilder(long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }


    public SalesOrderBuilder setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;

        return this;
    }

    public SalesOrderBuilder setUserId(long userId) {
        this.userId = userId;

        return  this;
    }

    public SalesOrderBuilder setCreationDate(Date creationDate) {
        this.creationDate = creationDate;

        return this;
    }

    public SalesOrderBuilder setLimit(String limit) {
        this.limit = limit;

        return this;
    }

    public SalesOrderBuilder setOrderStatus(long orderStatus) {
        this.orderStatus = orderStatus;

        return this;
    }


    public SalesOrder build() {
        return new SalesOrder(
                salesOrderId,
                userId,
                creationDate,
                limit,
                orderStatus
                );
    }
}
