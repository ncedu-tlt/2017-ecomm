import {Component, Input, OnInit} from "@angular/core";
import {UsersService} from "../../services/users.service";
import SalesOrderModel from "../../models/sales-order.model";
import TableModel from "../data-table/models/table.model";

@Component ({
    selector: 'nc-user-orders',
    templateUrl: 'user-orders.component.html'
})

export class UserOrdersComponent implements OnInit {

    @Input()
    userId: number;

    selectSalesOrder: SalesOrderModel;

    model: TableModel = {
        data: [],
        columns: [
            {
                name: 'Date',
                key: 'creationDate'
            },
            {
                name: 'Amount',
                key: 'totalAmount',
            },
            {
                name: 'Status',
                key: 'statusName'
            }
        ]
    };


    constructor(private usersService: UsersService){}

    ngOnInit(): void {
        this.usersService.getOrdersByUserId(this.userId).then(salesOrders => this.model.data = salesOrders);
    }

    onSelect(salesOrder: SalesOrderModel): any {
        this.selectSalesOrder = salesOrder;
    }
}