package ru.ncedu.ecomm.data.models.builders;

import ru.ncedu.ecomm.data.models.OrderItem;

public class OrderItemBuilder {

    private long productId;
    private long salesOrderId;
    private int quantity;
    private long standardPrice;
    private String name;
    private long price;
    private String imgUrl;
    private long discount;

    public OrderItemBuilder() {
    }

    public OrderItemBuilder setProductId(long productId) {
        this.productId = productId;
        return this;
    }

    public OrderItemBuilder setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
        return this;
    }

    public OrderItemBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderItemBuilder setStandardPrice(long standardPrice) {
        this.standardPrice = standardPrice;
        return this;
    }

    public OrderItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public OrderItemBuilder setPrice(long price) {
        this.price = price;
        return this;
    }

    public OrderItemBuilder setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public OrderItemBuilder setDiscount(long discount) {
        this.discount = discount;
        return this;
    }

    public OrderItem build(){
        return new OrderItem(productId, salesOrderId, quantity, standardPrice, name, price, imgUrl, discount);
    }
}
