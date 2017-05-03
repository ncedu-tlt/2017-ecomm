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
    isSent: boolean = false;
    isError: boolean = false;
    isAdd: boolean = false;
    isEdit: boolean = false;

    group: CharGroupModel = {characteristicGroupId: null, characteristicGroupName: ''};

    constructor(private route: ActivatedRoute,
                private charGroupService: CharGroupService,
                private location: Location) {
    };

    ngOnInit(): void {
        let id: any = this.route.snapshot.params['id'];
        if (id == 'addition') {
            this.isAdd = true;
        }
        else {
            this.charGroupService.getCharacteristicGroup(+id)
                .then(group => {
                    this.group = group;
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
        if (!this.group.characteristicGroupName.trim()) return;
        this.charGroupService
            .addCharacteristicGroup(this.group.characteristicGroupName)
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
        if (this.group) {
            this.charGroupService
                .updateCharacteristicGroup(this.group)
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