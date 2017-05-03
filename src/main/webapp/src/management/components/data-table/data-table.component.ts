import {Component, EventEmitter, Input, Output} from "@angular/core";
import TableModel from "./models/table.model";

@Component({
    selector: 'nc-data-table',
    templateUrl: 'data-table.component.html',
    styleUrls: ['data-table.component.css']
})
export class DataTableComponent {
    @Input('selectedItem')
    selectedItem: any;

    @Input()
    model: TableModel;

    @Output('select')
    onSelectEmitter = new EventEmitter<any>();

    onSelect(data: any): void {
        if (data === this.selectedItem) {
            this.onSelectEmitter.emit(null);
        }
        else {
            this.onSelectEmitter.emit(data);
        }
    }

    getValue(row: any, keyString: string): any {
        const keys = keyString.split('.');
        let value = row;

        keys.forEach(key => value = value[key]);

        return value;
    }

}