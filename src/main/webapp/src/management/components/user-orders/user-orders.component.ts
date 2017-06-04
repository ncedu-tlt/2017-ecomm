import {Component, Input, OnInit} from "@angular/core";
import {UsersService} from "../../services/users.service";
import SalesOrderModel from "../../models/sales-order.model";
import TableModel from "../data-table/models/table.model";
import {Router} from "@angular/router";

@Component ({
    selector: 'nc-user-orders',
    templateUrl: 'user-orders.component.html',
    styleUrls: ['user-orders.component.css']
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
                name: 'Amount ($)',
                key: 'totalAmount'
            },
            {
                name: 'Status',
                key: 'statusName'
            }
        ]
    };


    constructor(private usersService: UsersService,
                private router: Router){}

    ngOnInit(): void {
        this.usersService.getOrdersByUserId(this.userId).then(salesOrders => this.model.data = salesOrders);
    }

    onSelect(salesOrder: SalesOrderModel): any {
        this.selectSalesOrder = salesOrder;
    }

    onEdit(): void {
        this.router.navigate(['/order', this.selectSalesOrder.salesOrderId]);
    }

    selectState(): any {
        return !this.selectSalesOrder;
    }
}