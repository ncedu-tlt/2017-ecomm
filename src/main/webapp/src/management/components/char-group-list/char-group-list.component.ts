import {Component, OnInit, Input, ViewChild} from "@angular/core";
import TableModel from "../data-table/models/table.model";
import CharGroupModel from "../../models/char-group.model";
import {CharGroupService} from "../../services/char-group.service";
import {Router} from "@angular/router";
import {SemanticModalComponent} from "ng-semantic";

@Component({
    selector: 'nc-char-group-list',
    templateUrl: 'char-group-list.component.html',
    styleUrls: ['char-group-list.component.css']
})
export class CharGroupListComponent implements OnInit {
    charGroupModel: CharGroupModel;

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

    @ViewChild('modalWindow')
    modalWindow: SemanticModalComponent;
    modalContent: string;

    constructor(private charGroupService: CharGroupService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.charGroupService.getCharacteristicGroups()
            .then(charGroups => this.model.data = charGroups);
    }

    onSelect(charGroup: CharGroupModel): void {
        this.charGroupModel = charGroup;
    }

    onAddition(): void {
        this.router.navigate(['/char-group-editor', 'addition']);
    }

    onEdit(): void {
        if (this.charGroupModel) {
            this.router.navigate(['/char-group-editor', this.charGroupModel.characteristicGroupId]);
        }
        else{
            this.modalWindow.title = 'Error editing item';
            this.modalContent = 'Please select an item before editing it.';
            this.modalWindow.show({blurring: true});
        }
    }

    onDelete(): void {
        if (this.charGroupModel) {
            this.charGroupService
                .deleteCharacteristicGroup(this.charGroupModel.characteristicGroupId)
                .then(() => {
                    this.model.data = this.model.data.filter(group => group !== this.charGroupModel);
                    this.charGroupModel = null;
                })
                .catch(() =>{
                    this.modalWindow.title = 'Error removing item';
                    this.modalContent = 'This item can not yet be removed.';
                    this.modalWindow.show({blurring: true});
                })
        }
        else{
            this.modalWindow.title = 'Error removing item';
            this.modalContent = 'Please select an item before you delete it.';
            this.modalWindow.show({blurring: true});
        }
    }
}