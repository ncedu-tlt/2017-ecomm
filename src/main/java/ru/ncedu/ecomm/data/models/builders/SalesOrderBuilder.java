package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.SalesOrder;

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
        instance.setSalesOrderId(userId);
        return this;
    }
    public SalesOrderBuilder setCreationDate(long creationDate){
        if (instance == null){
            instance = new SalesOrder();
        }
        instance.setSalesOrderId(creationDate);
        return this;
    }
    public SalesOrderBuilder setLimit(long limit){
        if (instance == null){
            instance = new SalesOrder();
        }
        instance.setSalesOrderId(limit);
        return this;
    }
    public SalesOrderBuilder setOrederStatusId(long orederStatusId){
        if (instance == null){
            instance = new SalesOrder();
        }
        instance.setSalesOrderId(orederStatusId);
        return this;
    }

    public SalesOrder build(){
        return instance;
    }
}
