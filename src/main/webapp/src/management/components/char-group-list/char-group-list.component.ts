import {Component, OnInit, Input} from '@angular/core';
import TableModel from "../data-table/models/table.model";
import CharGroupModel from "../../models/char-group.model";
import {CharGroupService} from "../../services/char-group.service";

@Component({
    selector: 'nc-char-group-list',
    templateUrl: 'char-group-list.component.html',
})
export class CharGroupListComponent implements OnInit {
    charGroupModel: CharGroupModel;
    groupService: CharGroupService;
    message: string;

    @Input() model: TableModel = {
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

    constructor(private charGroupService: CharGroupService) {
        this.groupService = this.charGroupService;
    }

    ngOnInit(): void {
        this.charGroupService.getCharacteristicGroups()
            .then(charGroups => this.model.data = charGroups);
    }

    onSelect(charGroup: CharGroupModel): void {
        this.charGroupModel = charGroup;
    }

    onAddition(groupName: string): void {
        groupName = groupName.trim();
        if (!groupName) return;
        this.charGroupService
            .addCharacteristicGroup(groupName, this.model.data.length)
            .subscribe(group  => this.model.data.push(group));
    }

    onDelete(): void {
        this.groupService
            .deleteCharacteristicGroup(this.charGroupModel.characteristicGroupId)
            .then(() => {
                this.model.data = this.model.data.filter(group => group !== this.charGroupModel);
                this.charGroupModel = null;
            });
    }

    messageOnDelete(groupName: string): void {
        this.message = groupName + ' was delete.';
        console.log(this.message);
    }
}
