import {Component, OnInit, Output, Input, ViewChild} from "@angular/core";
import {CategoryService} from "../../services/category.service";
import {EventEmitter} from "@angular/forms/src/facade/async";
import CategoryModel from "../../models/category.model";
import {Router} from "@angular/router";
import {SemanticModalComponent} from "ng-semantic";

@Component({
    selector: 'nc-categories-tree',
    templateUrl: 'categories-tree.component.html'
})
export class CategoriesTreeComponent implements OnInit {
    selectedCategory: CategoryModel;

    @Output('choseCategory')
    choseCategory = new EventEmitter<number>();

    constructor(private categoryService: CategoryService,
                private router: Router) {
    }

    categories: CategoryModel[] = [];
    @ViewChild('modalWindow')
    modalWindow: SemanticModalComponent;

    ngOnInit() {
        this.getCategories();
        this.choseCategory.emit(null);
    }

    getCategories(): void {
        this.categoryService.getAll()
            .then(categories => {
                categories.forEach((item, i, categories) =>
                    categories[i].children = []);
                this.categories = categories
            });
    }

    onSelect(category: any): void {
        if (this.selectedCategory != category.node.data) {
            this.selectedCategory = category.node.data;
            this.choseCategory.emit(this.selectedCategory.categoryId);
        }
        else {
            this.selectedCategory = null;
            this.choseCategory.emit(null);
        }
    }

    onAdd(): void {
        let params = {id: (this.selectedCategory && this.selectedCategory.categoryId) || 0, action: 'addition'};
        this.router.navigate(['/category-editor'], {queryParams: params});
    }

    onEdit(): void {
        let params = {id: (this.selectedCategory && this.selectedCategory.categoryId) || 0, action: 'edit'};
        this.router.navigate(['/category-editor'], {queryParams: params});
    }

    public onDelete(): void {
        this.categoryService
            .deleteCategory(this.selectedCategory.categoryId)
            .then(() => {
                this.getCategories();
                this.selectedCategory = null;
                this.choseCategory.emit(null);
            }).catch(() => {
            this.modalWindow.show({blurring: true});
        });
    }
}
