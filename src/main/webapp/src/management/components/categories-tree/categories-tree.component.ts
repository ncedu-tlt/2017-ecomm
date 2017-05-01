import {Component, OnInit} from "@angular/core";
import {CategoryService} from "../../services/category.service";

@Component({
    selector: 'nc-categories-tree',
    templateUrl: 'categories-tree.component.html'
})
export class CategoriesTreeComponent implements OnInit {
    constructor(private categoryService: CategoryService) { }

    categories:any[] = [];

    ngOnInit() {
        this.categoryService.getAll().then(categories => this.categories = categories);
    }

}
