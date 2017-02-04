package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.data.models.SalesOrder;

import java.math.BigDecimal;
import java.sql.Date;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ShoppingCartIconService {
    private long userId;
    private long productId;
    private BigDecimal limit;
    private Date creationDate;


    public ShoppingCartIconService(long userId, long productId) {
        this.userId = userId;
        this.productId = productId;

        limit = new BigDecimal("50000.00"); //временно
        creationDate = new Date(System.currentTimeMillis());
    }

    public ShoppingCartIconService(long userId) {
        this.userId = userId;
    }

    public void addToShoppingCart() {
        if (searchSalesOrderByUserId(this.userId)) {
            addProductToOrderItem(this.productId);
        } else {
            SalesOrder salesOrder = addToSalesOrder();
            getDAOFactory().getSalesOrderDAO().addSalesOrder(salesOrder);
            addProductToOrderItem(this.productId);
        }
    }

    private boolean searchSalesOrderByUserId(long userId) {
        SalesOrder salesOrder = getDAOFactory().getSalesOrderDAO().getSalesOrderByUserId(userId);
        return salesOrder != null;
    }

    private void addProductToOrderItem(long productId) {
        long salesOrderId = getSalesOrderId(this.userId);
        OrderItem orderItem = addToOrderItem(productId, salesOrderId);
        getDAOFactory().getOrderItemsDAO().addOrderItem(orderItem);
    }

    private SalesOrder addToSalesOrder() {
        SalesOrder saleOrder = new SalesOrder();

        saleOrder.setUserId(this.userId);
        saleOrder.setCreationDate(this.creationDate);
        saleOrder.setLimit(this.limit);
        saleOrder.setOrderStatusId(this.getOrderStatusEntering());

        return saleOrder;
    }

    private OrderItem addToOrderItem(long productId, long salesId) {
        int defaultQuantity = 1;

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productId);
        orderItem.setSalesOrderId(salesId);
        orderItem.setQuantity(defaultQuantity);

        return orderItem;
    }

    public long getSalesOrderId(long userId) {
        SalesOrder salesOrder = getDAOFactory().getSalesOrderDAO().getSalesOrderByUserId(userId);
        return salesOrder.getSalesOrderId();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    private int getOrderStatusEntering() {
        return 1;
    }
}
