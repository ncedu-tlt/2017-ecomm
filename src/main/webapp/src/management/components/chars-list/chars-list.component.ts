import {Component, OnInit, ViewChild} from "@angular/core";
import TableModel from "../data-table/models/table.model";
import {CharacteristicService} from "../../services/characteristic.service";
import CharacteristicModel from "../../models/characteristic.model";
import {ActivatedRoute, Router} from "@angular/router";
import CategoryModel from "../../models/category.model";
import {CategoriesTreeComponent} from "../categories-tree/categories-tree.component";

@Component({
    selector: 'nc-characteristic-list',
    templateUrl: 'chars-list.component.html',
    styleUrls: ['chars-list.component.css']
})
export class CharacteristicListComponent implements OnInit {
    selectedCategory: CategoryModel;
    selectedCharacteristic: CharacteristicModel;

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

    @ViewChild(CategoriesTreeComponent)
    categoryTree: CategoriesTreeComponent;

    constructor(private characteristicService: CharacteristicService,
                private route: ActivatedRoute,
                private router: Router) {
    }

    ngOnInit(): void {
        let categoryId: any = this.route.snapshot.params['categoryId'];
        let groupId: any = this.route.snapshot.params['groupId'];
        this.characteristicService.getCharByCategoryAndGroup(categoryId, groupId)
            .then(characteristics => this.characteristicTableModel.data = characteristics);
    }

    onSelectCategory(category: any): void {
        this.selectedCategory = category;
    }

    onSelectCharacteristic(characteristic: CharacteristicModel): void {
        this.selectedCharacteristic = characteristic;
    }

    onAddCategory(): void {
        let params = {id: (this.selectedCategory && this.selectedCategory.categoryId) || 0, action: 'addition'};
        this.router.navigate(['/category-editor'], {queryParams : params});
    }

    onEditCategory(): void {
        let params = {id: (this.selectedCategory && this.selectedCategory.categoryId) || 0, action: 'edit'};
        this.router.navigate(['/category-editor'], {queryParams : params});
    }

    onDeleteCategory(): void {
        this.categoryTree.onDelete(this.selectedCategory.categoryId);
    }

    onAddCharacteristic(): void {

    }

    onEditCharacteristic(): void {

    }

    onDeleteCharacteristic(): void {

    }

}
