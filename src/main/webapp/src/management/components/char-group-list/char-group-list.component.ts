import {Component, OnInit} from '@angular/core';
import TableModel from "../data-table/models/table.model";
import CharGroupModel from "../../models/char-group.model";
import {CharGroupService} from "../../services/charGroup.service";

@Component({
    selector: 'nc-charGroup-list',
    templateUrl: 'char-group-list.component.html'
})
export class CharGroupListComponent implements OnInit{
    charGroupModel: CharGroupModel;

    model: TableModel = {
        data: [],
        columns: [
            {
                name: 'ID',
                key: 'characteristicGroupId'
            },
            {
                name: 'Name',
                key: 'characteristicGroupName'
            }
        ]
    };

    constructor(private charGroupService: CharGroupService){
    }

    ngOnInit(): void {
        this.charGroupService.getCharacteristicGroups()
            .then(charGroups => this.model.data = charGroups);
    }

    onSelect(charGroup: CharGroupModel): void{
        this.charGroupModel = charGroup;
    }
}
