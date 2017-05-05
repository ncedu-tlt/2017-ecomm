import OrderItemModel from "./order-item.model";
export default class SalesOrderModel {
    userId: number;
    salesOrderId: number;
    statusName: string;
    totalAmount: number;
    creationDate: string;
    orderItems: OrderItemModel;
}
