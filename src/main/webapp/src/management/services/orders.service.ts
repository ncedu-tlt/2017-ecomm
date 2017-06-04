
import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions} from "@angular/http";
import SalesOrderModel from "../models/sales-order.model";
import OrderItemModel from "../models/order-item.model";
import StatusModel from "../models/status.model";
declare const contextPath: string;

@Injectable()
export class OrdersService {
    private ordersUrl = `${contextPath}/rest/ecomm/v2/salesOrders`;
    private orderItemsUrl = `${contextPath}/rest/ecomm/v2/salesOrders/orderItems`;
    private statusesUrl = `${contextPath}/rest/ecomm/v2/statuses`;
    private headers = new Headers({ 'Content-Type': 'application/json' });

    options: RequestOptions = new RequestOptions({headers: this.headers});

    constructor(private http:Http){

    }

    getOrders(): Promise<SalesOrderModel[]> {
        return this.http.get(this.ordersUrl).toPromise()
            .then(response => response.json() as SalesOrderModel[])
            .catch(this.handleError);
    }

    getOrder(id:number): Promise<SalesOrderModel>{
        const url = `${this.ordersUrl}/${id}`;
        return this.http.get(url).toPromise()
                .then(response => response.json() as SalesOrderModel)
                .catch(this.handleError);
    }

    getOrderItems(salesOrderId:number): Promise<OrderItemModel[]>{
        const url = `${this.orderItemsUrl}/${salesOrderId}`;
        return this.http.get(url).toPromise()
            .then(response => response.json() as OrderItemModel[])
            .catch(this.handleError);
    }

    getStatuses(): Promise<StatusModel[]> {
        return this.http.get(this.statusesUrl).toPromise()
            .then(response => response.json() as StatusModel[])
            .catch(this.handleError);
    }

    updateOrder(order: SalesOrderModel): Promise<SalesOrderModel>{
        return this.http.put(this.ordersUrl,order,this.options).toPromise()
            .then(response => response.json() as SalesOrderModel)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

    deleteOrder(orderId:number): Promise<void>{
        const url = `${this.ordersUrl}/${orderId}`;
        return this.http.delete(url, {headers: this.headers})
            .toPromise()
            .then(() => null)
            .catch(this.handleError);
    }

}