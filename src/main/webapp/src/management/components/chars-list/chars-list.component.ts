import {Component, OnInit} from "@angular/core";
import TableModel from "../data-table/models/table.model";
import {CharacteristicService} from "../../services/characteristic.service";
import CharacteristicModel from "../../models/characteristic.model";
import {FormControl} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'nc-characteristic-list',
    templateUrl: 'chars-list.component.html',
    styleUrls: ['chars-list.component.css']
})
export class CharacteristicListComponent implements OnInit{
    selectedCharacteristic: CharacteristicModel;

    selectedCategoryId: number;

    characteristicTableModel: TableModel = {
        data: [],
        columns: [
            {
                name: 'Name',
                key: 'characteristicName'
            },
            {
                name: 'Filterable',
                key: 'filterable'
            }
        ]
    };

    constructor(private characteristicService: CharacteristicService,
                private route: ActivatedRoute) {}

    ngOnInit(): void {
        let categoryId: any = this.route.snapshot.params['categoryId'];
        let groupId: any = this.route.snapshot.params['groupId'];
        this.characteristicService.getCharByCategoryAndGroup(categoryId, groupId)
            .then(characteristics => this.characteristicTableModel.data = characteristics);
    }

    onSelect(characteristic: CharacteristicModel): void {
        this.selectedCharacteristic = characteristic;
    }

    getValue(data: CharacteristicModel, keyString: string): any {
        const keys = keyString.split('.');
        let value = data;

        keys.forEach(key => value = value[key]);
        if(typeof value == 'boolean'){
            return new FormControl(value);
        }
        return value;
    }

    onSelectedCategory(event: number){
        this.selectedCategoryId = event;
    }

}
