import {Component, OnInit} from '@angular/core';
import CharGroupModel from "../../models/char-group.model";
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';
import {CharGroupService} from "../../services/char-group.service";

@Component({
    selector: 'nc-char-group-editor',
    templateUrl: 'char-group-editor.component.html'
})

export class CharGroupEditorComponent implements OnInit{
    submit: string;
    action: string;

    group: CharGroupModel = new CharGroupModel();

    constructor(private route: ActivatedRoute,
                private charGroupService: CharGroupService,
                private location: Location) {
    };

    ngOnInit(): void {
        let id: any = this.route.snapshot.params['id'];
        if (id == 'addition') {
            this.action = 'addition';
        }
        else {
            this.charGroupService.get(+id)
                .then(group => {
                    this.group = group;
                    this.action = 'edit';
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
        if (!this.group.characteristicGroupName.trim()) return;
        this.charGroupService
            .add(this.group.characteristicGroupName)
            .then(() => {
                this.back();
            })
            .catch(() => {
                this.submit = 'error';
            });
    }

    edit(): void {
        if (this.group) {
            this.charGroupService
                .update(this.group)
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