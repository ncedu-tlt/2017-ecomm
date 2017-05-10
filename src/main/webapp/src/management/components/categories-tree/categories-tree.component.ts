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
    selectedCategory: CategoryModel;

    @Output('select')
    onSelectEmitter = new EventEmitter<CategoryModel>();

    constructor(private categoryService: CategoryService) { }

    categories:CategoryModel[] = [];

    ngOnInit() {
        this.categoryService.getAll()
            .then(categories => {
                categories.forEach((item, i, categories) =>
                    categories[i].children = [] );
                this.categories = categories
            });
    }

    onSelect(category: any): void {
        if (category.node.data === this.selectedCategory) {
            this.onSelectEmitter.emit(null);
        }
        else {
            this.onSelectEmitter.emit(category.node.data);
        }
    }

    public onDelete(categoryId: number): void{
        this.categoryService
            .deleteCategory(categoryId)
            .then(() => {
                this.categories = this.categories.filter(category => category !== this.selectedCategory);
            });
    }
}
