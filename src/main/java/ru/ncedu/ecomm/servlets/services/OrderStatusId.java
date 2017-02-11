package ru.ncedu.ecomm.servlets.services;

//TODO: public
//TODO: почему используем пакет services под enum?
//TODO: enum олицетворяет статус, а не идентификатор
public enum OrderStatusId {
    ENTERING(1),
    SUBMITED(2), //TODO: IDEA подсвечивает и грамматические ошибки
    COMPLETED(3);

    private int orderStatusId; //TODO: и так понятно, что id относится к статусу

    OrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    //TODO: setter потерялся

    public int getStatusId() {
        return orderStatusId;
    }
}
