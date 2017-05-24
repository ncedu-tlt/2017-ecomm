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
        this.charsListService.getAllByCategoryId(categoryId)
            .then(charGroups => this.charsList = charGroups);
    }

    setTableData(characteristics: CharacteristicModel[]): void {
        this.charGroupTableModel.data = characteristics;
    }

    onSelectCharacteristic(characteristic: CharacteristicModel): void {
        this.selectedCharacteristic = characteristic;
    }

    onAddCharacteristic(): void {
        let params = {categoryId: this.selectedCategoryId,
            characteristicId: (this.selectedCharacteristic && this.selectedCharacteristic.characteristicId) || 0 ,
            action: 'addition'};
        this.router.navigate(['/char-editor'], {queryParams: params});
    }

    onEditCharacteristic(): void {
        let params = {categoryId: this.selectedCategoryId,
            characteristicId: (this.selectedCharacteristic && this.selectedCharacteristic.characteristicId) || 0 ,
            action: 'edit'};
        this.router.navigate(['/char-editor'], {queryParams: params});
    }

    onDeleteCharacteristic(): void {
        this.charsListService.delete(this.selectedCharacteristic.characteristicId)
            .then(() => {
                this.getCharsList(this.selectedCategoryId);
                this.selectedCharacteristic = null;
            });
    }

}
