package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.SalesOrder;

import java.util.Date;

public class SalesOrderBuilder {

    private SalesOrder instance;

    public SalesOrderBuilder setSalesOrderId(long salesOrderId){
        if (instance == null){
            instance = new SalesOrder();
        }
        instance.setSalesOrderId(salesOrderId);
        return this;
    }
    public SalesOrderBuilder setUserId(long userId){
        if (instance == null){
            instance = new SalesOrder();
        }
        instance.setUserId(userId);
        return this;
    }
    public SalesOrderBuilder setCreationDate(Date creationDate){
        if (instance == null){
            instance = new SalesOrder();
        }
        instance.setCreationDate(creationDate);
        return this;
    }
    public SalesOrderBuilder setLimit(String  limit){
        if (instance == null){
            instance = new SalesOrder();
        }
        instance.setLimit(limit);
        return this;
    }
    public SalesOrderBuilder setOrederStatusId(long orederStatusId){
        if (instance == null){
            instance = new SalesOrder();
        }
        instance.setOrderStatusId(orederStatusId);
        return this;
    }

    public SalesOrder build(){
        return instance;
    }
}
