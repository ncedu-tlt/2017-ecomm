import { Component, OnInit } from '@angular/core';
import CategoryModel from "../../models/category.model";
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';
import {CategoryService} from "../../services/category.service";

@Component({
    selector: 'category-editor',
    templateUrl: 'category-editor.component.html'
})
export class CategoryEditorComponent implements OnInit {
    isSent: boolean = false;
    isError: boolean = false;
    isAdd: boolean = false;
    isEdit: boolean = false;

    category: CategoryModel = new CategoryModel();

    constructor(private route: ActivatedRoute,
                private categoryService: CategoryService,
                private location: Location) {
    };

    ngOnInit(): void {
        let id: any = this.route.snapshot.params['id'];
        if (id == 'addition') {
            this.isAdd = true;
        }
        else {
            this.categoryService.get(+id)
                .then(category => {
                    this.category = category;
                    this.isEdit = true;
                })
                .catch(() => this.back())
        }
    }

    onSubmit(): void {
        if (this.isAdd) {
            this.addition();
        }
        if (this.isEdit) {
            this.edit();
        }
    }

    addition(): void {
        if (!this.category.name.trim()) return;
        this.categoryService
            .add(this.category.name)
            .then(() => {
                this.isError = false;
                this.isSent = true;
            })
            .catch(() => {
                this.isSent = false;
                this.isError = true;
            });
    }

    edit(): void {
        if (this.category) {
            this.categoryService
                .update(this.category)
                .then(() => this.back())
                .catch(() => {
                    this.isSent = false;
                    this.isError = true;
                });
        }
    }

    back(): void {
        this.location.back();
    }
}
