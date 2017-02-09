package ru.ncedu.ecomm.servlets.services;


enum OrderStatusId {
    ENTERING(1),
    SUBMITED(2),
    COMPLETED(3);

    private int orderStatusId;

    OrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public int getStatusId() {
        return orderStatusId;
    }
}
