package ru.ncedu.ecomm.servlets.services;

import ru.ncedu.ecomm.data.models.OrderItem;

import java.util.List;

import static ru.ncedu.ecomm.data.DAOFactory.getDAOFactory;

public class ProductToShoppingCart {
    private final long PRODUCT_ID;
    private final long SALES_ORDER_ID;
    private final long NEXT_SALES_ORDER_ID;

    public ProductToShoppingCart(long productId, long sales_order_id) {
        PRODUCT_ID = productId;
        SALES_ORDER_ID =sales_order_id;
        NEXT_SALES_ORDER_ID = getNextSalesOrderId();
    }

    public static long nextSalesOrderId() {
        List<OrderItem> orderItems = getDAOFactory().getOrderItemsDAO().getOrderItems();
        long maxSalesOrderId = 0;
        for(OrderItem orderItem : orderItems){
            if(maxSalesOrderId < orderItem.getSalesOrderId()){
                maxSalesOrderId = orderItem.getSalesOrderId();
            }
        }

        return ++maxSalesOrderId;
    }

    public void addToShoppingCart(){
        int quantityDefault = 0;
        OrderItem newOrderItem = new OrderItem(getProductId(), getSalesOrderId(), quantityDefault);
        getDAOFactory().getOrderItemsDAO().addOrderItem(newOrderItem);
    }

    public long getProductId() {
        return PRODUCT_ID;
    }

    public long getSalesOrderId() {
        return SALES_ORDER_ID;
    }

    public long getNextSalesOrderId() {
        return NEXT_SALES_ORDER_ID;
    }
}
