package ru.ncedu.ecomm.servlets.models;

public enum EnumOrderStatus {
    ENTERING(1),
    SUBMITTED(2),
    COMPLETED(3);

    private int orderStatus;

    EnumOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getStatus() {
        return orderStatus;
    }
}
