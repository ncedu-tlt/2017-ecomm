package ru.ncedu.ecomm.servlets.models.builders;

import ru.ncedu.ecomm.servlets.models.OrderItemViewModel;

public class OrderItemViewBuilder {

    private long productId;
    private long salesOrderId;
    private int quantity;
    private String name;
    private long price;
    private String imgUrl;

    public OrderItemViewBuilder() {
    }

    public OrderItemViewBuilder setProductId(long productId) {
        this.productId = productId;
        return this;
    }

    public OrderItemViewBuilder setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
        return this;
    }

    public OrderItemViewBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderItemViewBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public OrderItemViewBuilder setPrice(long price) {
        this.price = price;
        return this;
    }

    public OrderItemViewBuilder setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public OrderItemViewModel build(){
        return new OrderItemViewModel(productId, salesOrderId, quantity, name, price, imgUrl);
    }
}
