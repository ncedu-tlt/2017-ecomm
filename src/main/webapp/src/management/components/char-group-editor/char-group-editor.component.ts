import {Component} from '@angular/core';
import CharGroupModel from "../../models/char-group.model";
import {ActivatedRoute, Params} from "@angular/router";
import {CharGroupService} from "../../services/char-group.service";

@Component({
    selector: 'nc-char-group-editor',
    templateUrl: 'char-group-editor.component.html'
})

export class CharGroupEditorComponent {
    isSent: boolean = false;
    isError: boolean = false;
    isAdd: boolean = true;
    isEdit: boolean = false
    id: number = 1;

    group: CharGroupModel = {characteristicGroupId: null, characteristicGroupName: ''};

    constructor(private route: ActivatedRoute,
                private charGroupService: CharGroupService,) {
    };

    ngOnInit(): void {
        this.route.params
            .switchMap((params: Params) => this.charGroupService.getCharacteristicGroup(+params['id']))
            .subscribe(group => this.group = group);
    }

    onSubmit(): void {
        console.log(this.group.characteristicGroupName);
        if(this.isAdd){
            this.addition(this.group.characteristicGroupName);
        }
    }

    addition(groupName: string): void {
        if (!groupName.trim()) return;
        this.charGroupService
            .addCharacteristicGroup(groupName)
            .then(() => {
                this.isError = false;
                this.isSent = true
            })
            .catch(() => {
                this.isSent = false;
                this.isError = true
            });
    }

    edit(selectedCharGroupModel: CharGroupModel, groupName: string): void {
        if (selectedCharGroupModel) {
            this.charGroupService
                .addCharacteristicGroup(groupName)
                .then(() => {
                    this.isError = false;
                    this.isSent = true
                })
                .catch(() => {
                    this.isSent = false;
                    this.isError = true
                });
        }
    }

    back(): void {
        this.isSent = false;
        this.isError = true;
        console.log(this.isError);
    }
}