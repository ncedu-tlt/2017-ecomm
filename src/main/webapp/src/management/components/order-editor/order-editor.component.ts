import {Component, OnInit} from "@angular/core";
import SalesOrderModel from "../../models/sales-order.model";
import OrderItemModel from "../../models/order-item.model";
import TableModel from "../data-table/models/table.model";
import {ActivatedRoute} from "@angular/router";
import {OrdersService} from "../../services/orders.service";
import StatusModel from "../../models/status.model";
import {Location} from "@angular/common";

@Component({
    selector: 'nc-order-editor',
    templateUrl: 'order-editor.component.html',
    styleUrls: ['order-editor.component.css']
})

export class OrderEditorComponent implements OnInit {

    salesOrderId: number;

    salesOrder: SalesOrderModel = new SalesOrderModel();
    orderItems: OrderItemModel[];
    orderStatuses: StatusModel[];

    model: TableModel = {
        data: [],
        columns: [
            {
                name: 'Product name',
                key: 'name'
            },
            {
                name: 'Quantity',
                key: 'quantity'
            },
            {
                name: 'Prise',
                key: 'price'
            }

        ]
    };

    constructor(private route: ActivatedRoute, private orderService: OrdersService,
                private location: Location) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.salesOrderId = +params['id'];
        });

        this.salesOrder.orderStatus = new StatusModel;
        this.orderService.getStatuses().then(statuses => this.orderStatuses = statuses);

        this.orderService.getOrder(this.salesOrderId).then(order => this.salesOrder = order);

        this.salesOrder.orderItems = new OrderItemModel();
        this.orderService.getOrderItems(this.salesOrderId).then(orderItems => this.model.data = orderItems);


    }


    onSave(): void {
        this.orderService.updateOrder(this.salesOrder).then(() => {
            this.location.back();
        });
    }

    onCancel(): void {
        this.location.back();
    }

    onDelete(): void {
        this.orderService.deleteOrder(this.salesOrderId).then(this.onCancel.bind(this));
    }


}