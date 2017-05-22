import {Component, OnInit} from "@angular/core";
import TableModel from "../data-table/models/table.model";
import {CharsListService} from "../../services/charsList.service";
import CharacteristicModel from "../../models/characteristic.model";
import {ActivatedRoute, Router} from "@angular/router";
import CharacteristicListModel from "../../models/charsListModel";

@Component({
    selector: 'nc-charGroup-list',
    templateUrl: 'chars-list.component.html'
})
export class CharsListComponent implements OnInit {
    selectedCategoryId: number;
    selectedCharacteristic: CharacteristicModel;

    charsList: CharacteristicListModel[];

    charGroupTableModel: TableModel = {
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

    constructor(private charsListService: CharsListService,
                private route: ActivatedRoute,
                private router: Router) {
    }

    public getCharGroups(categoryId: number): void {
        this.selectedCategoryId = categoryId;
        this.getCharsList(categoryId);
    }

    ngOnInit(): void {
    }

    getCharsList(categoryId: number): void{
        this.charsListService.getByCategoryId(categoryId)
            .then(charGroups => this.charsList = charGroups);
    }

    setTableData(characteristics: CharacteristicModel[]): void {
        this.charGroupTableModel.data = characteristics;
    }

    onSelectCharacteristic(characteristic: CharacteristicModel): void {
        this.selectedCharacteristic = characteristic;
    }

    onAddCharacteristic(): void {
        this.router.navigate(['/char-editor', 'addition']);
    }

    onEditCharacteristic(): void {
        this.router.navigate(['/char-editor', this.selectedCharacteristic.characteristicId]);
    }

    onDeleteCharacteristic(): void {
        this.charsListService.delete(this.selectedCharacteristic.characteristicId)
            .then(() => {
                this.getCharsList(this.selectedCategoryId);
                this.selectedCharacteristic = null;
            });
    }

}
