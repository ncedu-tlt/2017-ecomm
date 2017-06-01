import OrderItemModel from "./order-item.model";
import StatusModel from "./status.model";
export default class SalesOrderModel {
    userId: number;
    userName: string;
    salesOrderId: number;
    totalAmount: number;
    creationDate: string;
    orderItems: OrderItemModel;
    orderStatus: StatusModel;
}
