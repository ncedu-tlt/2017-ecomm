import {Component, OnInit} from '@angular/core';
import CategoryModel from "../../models/category.model";
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';
import {CategoryService} from "../../services/category.service";
import {FormControl, Validators, FormBuilder, FormGroup} from "@angular/forms";

@Component({
    selector: 'nc-category-editor',
    templateUrl: 'category-editor.component.html',
    styleUrls: ['category-editor.component.css']
})
export class CategoryEditorComponent implements OnInit {
    submit: string;
    action: string;
    selection: number;

    selectDisabled: FormControl = new FormControl(false);

    categories: CategoryModel[];

    category: CategoryModel = new CategoryModel();

    formFeedback: FormGroup;

    constructor(private route: ActivatedRoute,
                private categoryService: CategoryService,
                private location: Location,
                private formBuilder: FormBuilder) {
        let validator = [Validators.required, Validators.minLength(5)];
        this.formFeedback = formBuilder.group({
            textControl: ["", Validators.compose(validator)]
        });
    };

    ngOnInit(): void {
        let id: any = this.route.snapshot.params['id'];
        if (id == 'addition') {
            this.action = 'addition';
            this.getCategories();
        }
        else {
            this.categoryService.get(+id)
                .then(category => {
                    this.category = category;
                    this.getCategories();
                    this.action = 'edit';
                });
        }
    }

    getCategories(): void {
        this.categoryService.getAll()
            .then(categories => this.categories = categories)
            .catch(this.categories = null);
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
        if (!this.category.name.trim()) return;
        this.categoryService
            .add(this.category.name)
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
