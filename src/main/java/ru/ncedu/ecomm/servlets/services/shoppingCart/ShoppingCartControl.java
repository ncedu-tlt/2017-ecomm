package ru.ncedu.ecomm.servlets.services.shoppingCart;

interface ShoppingCartControl {
    boolean searchSalesOrderByUserId(long userId);

    long getSalesOrderId(long userId);

    long getUserId();

    int getOrderStatus();
}
