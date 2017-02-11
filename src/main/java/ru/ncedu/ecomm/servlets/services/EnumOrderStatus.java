package ru.ncedu.ecomm.servlets.services;

//TODO: public
//TODO: почему используем пакет services под enum?
//TODO: enum олицетворяет статус, а не идентификатор
public enum EnumOrderStatus {
    ENTERING(1),
    SUBMITTED(2),
    COMPLETED(3);

    private int orderStatus; //TODO: и так понятно, что id относится к статусу

    EnumOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    //TODO: setter потерялся

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getStatus() {
        return orderStatus;
    }
}
