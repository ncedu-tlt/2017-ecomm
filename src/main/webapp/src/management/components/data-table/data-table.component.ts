import {Component, EventEmitter, Input, Output} from "@angular/core";
import TableModel from "./models/table.model";

@Component({
    selector: 'nc-data-table',
    templateUrl: 'data-table.component.html',
    styleUrls: ['data-table.component.css']
})
export class DataTableComponent {

    private selectedRow: any;

    @Input()
    model: TableModel;

    @Output('select')
    onSelectEmitter = new EventEmitter<any>();

    onSelect(row: HTMLTableRowElement, data: any): void {
        if (data === this.selectedRow) {
            row.classList.remove('active');
            this.selectedRow = null;
            this.onSelectEmitter.emit(null);
        }
        else {
            this.onSelectEmitter.emit(data);
            this.selectedRow = data;
        }
    }

    getValue(row: any, keyString: string): any {
        const keys = keyString.split('.');
        let value = row;

        keys.forEach(key => value = value[key]);

        return value;
    }

}