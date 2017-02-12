package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.OrderItemViewModel;
import ru.ncedu.ecomm.servlets.models.SalesOrderViewModel;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class SalesOrderViewBuilder {

    private long userId;
    private long salesOrderId;
    private Date creationDate;
    private BigDecimal limit;
    private List<OrderItemViewModel> orderItems;
    private long totalAmount;

    public SalesOrderViewBuilder(){}

    public SalesOrderViewBuilder setUserId(long userId){
        this.userId = userId;
        return this;
    }

    public SalesOrderViewBuilder setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
        return this;
    }

    public SalesOrderViewBuilder setLimit(BigDecimal limit) {
        this.limit = limit;
        return this;
    }

    public SalesOrderViewBuilder setOrderItems(List<OrderItemViewModel> orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    public SalesOrderViewBuilder setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public SalesOrderViewBuilder setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public SalesOrderViewModel build(){
        return new SalesOrderViewModel(userId, salesOrderId, creationDate, limit, orderItems, totalAmount);
    }
}
