import {Component, ViewChild} from '@angular/core';
import {CharsListComponent} from "../chars-list/chars-list.component";


@Component({
    selector: 'product-model-editor',
    templateUrl: 'product-model-editor.component.html',
    styleUrls: ['product-model-editor.component.css']
})
export class ProductModelEditorComponent {

    @ViewChild(CharsListComponent) charsList: CharsListComponent;
    selectedCategoryId: number;

    selectCategory(categoryId: number): void{
        this.charsList.getCharGroups(categoryId);
        this.selectedCategoryId = categoryId;
    }
}
