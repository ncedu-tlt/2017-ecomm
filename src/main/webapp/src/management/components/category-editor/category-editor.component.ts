import {Component, OnInit, OnChanges, SimpleChanges} from "@angular/core";
import CategoryModel from "../../models/category.model";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {CategoryService} from "../../services/category.service";

@Component({
    selector: 'nc-category-editor',
    templateUrl: 'category-editor.component.html',
    styleUrls: ['category-editor.component.css']
})
export class CategoryEditorComponent implements OnInit{
    action: string;
    submit: string;
    category: CategoryModel = new CategoryModel();
    categories: CategoryModel[];

    constructor(private route: ActivatedRoute,
                private categoryService: CategoryService,
                private location: Location) {
    };

    ngOnInit(): void {
        this.categoryService.getAll()
            .then((categories) => {
                this.categories = categories;
            });
        this.route.queryParams.subscribe(params => {
            if(+params['id'] != 0)
                this.category.parentId = +params['id'];
            this.action = params['action'];
        });
        if (this.category.parentId) {
            this.categoryService.get(this.category.parentId)
                .then(category => {
                    if (this.action == 'edit') {
                        this.category = category;
                        this.category.parentId = category.categoryId;
                    }
                });
        }
    }


    onSubmit(): void {
        if (this.action == 'addition') {
            this.addition();
        }
        if (this.action == 'edit') {
            this.edit();
        }
    }

    addition(): void {
        this.categoryService
            .add(this.category)
            .then(() => this.back())
            .catch(() => {
                this.submit = 'error';
            });
    }

    edit(): void {
        if (this.category) {
            this.categoryService
                .update(this.category)
                .then(() => this.back())
                .catch(() => {
                    this.submit = 'error';
                });
        }
    }

    back(): void {
        this.location.back();
    }
}
