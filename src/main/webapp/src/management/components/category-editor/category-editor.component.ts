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
    categoryId: number;
    action: string;
    submit: string;
    category: CategoryModel = new CategoryModel();

    constructor(private route: ActivatedRoute,
                private categoryService: CategoryService,
                private location: Location,) {
    };

    ngOnInit(): void {
        this.route.queryParams.subscribe(params => {
             this.categoryId = params['id'];
             this.action = params['action'];
        });
        if(this.action == 'edit'){
            this.categoryService.get(this.categoryId)
                .then(category => this.category = category);
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
        if (!this.category.name.trim()) return;
        this.category.parentId = this.categoryId;
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
