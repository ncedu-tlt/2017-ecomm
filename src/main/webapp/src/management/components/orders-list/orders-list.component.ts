import {Component, OnInit, group} from "@angular/core";
import {Router} from "@angular/router";
import TableModel from "../data-table/models/table.model";
import {OrdersService} from "../../services/orders.service";
import {SalesOrderModel} from "../../models/sales-order.model";

@Component({
    selector: 'nc-orders-list',
    templateUrl: 'orders-list.component.html',
    styleUrls: ['orders-list.component.css']
})
export class OrderListComponent implements OnInit {

    selectOrder: SalesOrderModel;

    model: TableModel = {
        data: [],
        columns: [
            {
                name: 'Number',
                key: 'salesOrderId'
            },
            {
                name: 'Date',
                key: 'creationDate'
            },
            {
                name: 'User',
                key: 'userName'
            },
            {
                name: 'Amount',
                key: 'totalAmount'
            },
            {
                name: 'Status',
                key: 'orderStatus.name'
            }
        ]
    };

    constructor(private ordersService: OrdersService, private router: Router) {
    }

    ngOnInit(): void {
        this.ordersService.getOrders().then(orders => this.model.data = orders)
    }


    onSelect(order: SalesOrderModel): any {
        this.selectOrder = order;
    }

    onDelete(): void {
        if (this.selectOrder) {
            this.ordersService.deleteOrder(this.selectOrder.salesOrderId)
                .then(() => {
                    this.model.data = this.model.data.filter(group => group !== this.selectOrder);
                    this.selectOrder = null
                });
        }
    }


    onEdit(): void {
        if (this.selectOrder) {
            this.router.navigate(['/order', this.selectOrder.salesOrderId]);
        }
    }

    selectState(): any {
        return !this.selectOrder;
    }


}
