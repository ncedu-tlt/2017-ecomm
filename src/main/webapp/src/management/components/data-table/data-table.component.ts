import {Component, EventEmitter, Input, Output} from "@angular/core";
import TableModel from "./models/table.model";
import UserModel from "../../models/user.model";

@Component({
    selector: 'nc-data-table',
    templateUrl: 'data-table.component.html',
    styleUrls: ['data-table.component.css']
})
export class DataTableComponent {

    private selectedRow: UserModel;

    @Input()
    model: TableModel;

    @Output('select')
    onSelectEmitter = new EventEmitter<any>();

    onSelect(row: any): void {
        this.onSelectEmitter.emit(row);
        this.selectedRow = row;
        console.log(row.email);
        console.log(row.id);
    }

    getValue(row: any, keyString: string): any {
        const keys = keyString.split('.');
        let value = row;

        keys.forEach(key => value = value[key]);

        return value;
    }

}