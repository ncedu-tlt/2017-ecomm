package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.SalesOrderDAOObject;

import java.math.BigDecimal;
import java.sql.Date;

public class SalesOrderDAOObjectBuilder {

    private long salesOrderId;
    private long userId;
    private Date creationDate;
    private BigDecimal limit;
    private long orderStatus;

    public SalesOrderDAOObjectBuilder(){
    }

    SalesOrderDAOObjectBuilder(long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }


    public SalesOrderDAOObjectBuilder setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;

        return this;
    }

    public SalesOrderDAOObjectBuilder setUserId(long userId) {
        this.userId = userId;

        return  this;
    }

    public SalesOrderDAOObjectBuilder setCreationDate(Date creationDate) {
        this.creationDate = creationDate;

        return this;
    }

    public SalesOrderDAOObjectBuilder setLimit(BigDecimal limit) {
        this.limit = limit;

        return this;
    }

    public SalesOrderDAOObjectBuilder setOrderStatus(long orderStatus) {
        this.orderStatus = orderStatus;

        return this;
    }

    public SalesOrderDAOObject build() {
        return new SalesOrderDAOObject(
                salesOrderId,
                userId,
                creationDate,
                limit,
                orderStatus
                );
    }
}
