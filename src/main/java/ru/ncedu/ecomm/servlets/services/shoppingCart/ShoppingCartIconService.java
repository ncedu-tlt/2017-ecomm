package ru.ncedu.ecomm.servlets.services.shoppingCart;

import ru.ncedu.ecomm.data.models.OrderItem;
import ru.ncedu.ecomm.data.models.SalesOrder;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ShoppingCartIconService implements ShoppingCartControl {
    private long userId;
    private long productId;
    private long salesOrderId;
    private BigDecimal limit;
    private Date creationDate;

    public ShoppingCartIconService(long userId, long productId) {
        this.userId = userId;
        this.productId = productId;
        this.salesOrderId = getSalesOrderId(this.userId);
        limit = new BigDecimal("50000.00"); //временно
        creationDate = new Date(System.currentTimeMillis());
    }

    public ShoppingCartIconService(long userId) {
        this.userId = userId;
    }

    public void addToShoppingCart() throws SQLException {
        if (searchSalesOrderByUserId()) {
            addProductToOrderItem();
        } else {
            createSalesOrder();
            addProductToOrderItem();
        }
    }

    @Override
    public long getSalesOrderId(long userId) {
        SalesOrder salesOrder = getDAOFactory().getSalesOrderDAO().getSalesOrderByUserId(userId);
        return salesOrder.getSalesOrderId();
    }

    @Override
    public boolean searchSalesOrderByUserId() {
        SalesOrder salesOrder = getDAOFactory().getSalesOrderDAO().getSalesOrderByUserId(userId);
        return salesOrder != null;
    }

    private void addProductToOrderItem() throws SQLException {
        if (isProductAtOrderItem()) {
            incrementQuantityOrderItem();
        } else {
            addNewOrderItem();
        }
    }

    private void createSalesOrder() {
        SalesOrder salesOrder = addToSalesOrder();
        getDAOFactory().getSalesOrderDAO().addSalesOrder(salesOrder);
    }

    private void incrementQuantityOrderItem() throws SQLException {
        OrderItem orderItem = getDAOFactory().getOrderItemsDAO()
                .getOrderItemByUserConfig(productId, salesOrderId);
        int quantity = orderItem.getQuantity() + 1;
        orderItem.setQuantity(quantity);
        getDAOFactory().getOrderItemsDAO().updateOrderItem(orderItem);
    }

    private void addNewOrderItem() {
        OrderItem orderItem = addToOrderItem();
        getDAOFactory().getOrderItemsDAO().addOrderItem(orderItem);
    }

    private boolean isProductAtOrderItem() {
        return getDAOFactory().getOrderItemsDAO().isHaveProductId(productId);
    }

    private SalesOrder addToSalesOrder() {
        SalesOrder saleOrder = new SalesOrder();

        saleOrder.setUserId(this.userId);
        saleOrder.setCreationDate(this.creationDate);
        saleOrder.setLimit(this.limit);
        saleOrder.setOrderStatusId(this.getOrderStatus());

        return saleOrder;
    }

    private OrderItem addToOrderItem() {
        int minQuantity = 1;

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productId);
        orderItem.setSalesOrderId(salesOrderId);
        orderItem.setQuantity(minQuantity);

        return orderItem;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    public long getProductId() {
        return productId;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    @Override
    public int getOrderStatus() {
        return 1;
    }
}
