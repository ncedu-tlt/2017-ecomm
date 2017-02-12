package ru.ncedu.ecomm.servlets.services;

//TODO: public
//TODO: почему используем пакет services под enum?
public enum EnumOrderStatus {
    ENTERING(1),
    SUBMITTED(2),
    COMPLETED(3);

    private int orderStatus;

    EnumOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }


    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getStatus() {
        return orderStatus;
    }
}
