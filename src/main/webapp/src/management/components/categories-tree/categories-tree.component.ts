import {Component, OnInit, Output, Input} from "@angular/core";
import {CategoryService} from "../../services/category.service";
import {EventEmitter} from "@angular/forms/src/facade/async";
import CategoryModel from "../../models/category.model";

@Component({
    selector: 'nc-categories-tree',
    templateUrl: 'categories-tree.component.html'
})
export class CategoriesTreeComponent implements OnInit {

    @Input('selectedItem')
    selectedItem: any;

    @Output('select')
    onSelectEmitter = new EventEmitter<number>();

    constructor(private categoryService: CategoryService) { }

    categories:any[] = [];

    ngOnInit() {
        this.categoryService.getAll()
            .then(categories => this.categories = categories);
    }

    onSelect(data: any): void {
        if (data === this.selectedItem) {
            this.onSelectEmitter.emit(null);
        }
        else {
            this.onSelectEmitter.emit(data);
        }
    }

    public onDelete(categoryId: number): void{
        this.categoryService
            .deleteCategory(categoryId)
            .then(() => {
                this.categories = this.categories.filter(category => category !== this.selectedItem);
                this.selectedItem = null;
            })
            .catch(() => {
                console.error('Not done');
            });
    }
}
