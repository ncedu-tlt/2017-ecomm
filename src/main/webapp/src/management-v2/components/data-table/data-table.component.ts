import {Component, Input, Output, EventEmitter} from "@angular/core";
import TableModel from "./models/table.model";

@Component({
    selector: 'nc-data-table',
    templateUrl: 'data-table.component.html'
})
export class DataTableComponent {

    @Input()
    model: TableModel;

    @Output('select')
    onSelectEmitter = new EventEmitter<any>();

    onSelect(row: any): void {
        this.onSelectEmitter.emit(row);
    }

    getValue(row: any, keyString: string): any {
        const keys = keyString.split('.');
        let value = row;

        keys.forEach(key => value = value[key]);

        return value;
    }

}