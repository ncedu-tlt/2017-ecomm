import {Component, OnInit, Output} from "@angular/core";
import {CategoryService} from "../../services/category.service";
import {EventEmitter} from "@angular/forms/src/facade/async";
import {Event} from "@angular/platform-browser/src/facade/browser";

@Component({
    selector: 'nc-categories-tree',
    templateUrl: 'categories-tree.component.html'
})
export class CategoriesTreeComponent implements OnInit {

    @Output() onSelected = new EventEmitter<number>();

    constructor(private categoryService: CategoryService) { }

    categories:any[] = [];

    ngOnInit() {
        this.categoryService.getAll()
            .then(categories => this.categories = categories);
    }

    onActivate(event: any) {
        this.onSelected.emit(event.node.data.categoryId);
    }
}
