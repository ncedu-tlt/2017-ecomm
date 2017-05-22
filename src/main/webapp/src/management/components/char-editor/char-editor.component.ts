import { Component, OnInit } from '@angular/core';
import {CharsListService} from "../../services/charsList.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from '@angular/common';

@Component({
    selector: 'nc-char-editor',
    templateUrl: 'char-editor.component.html'
})
export class CharEditorComponent implements OnInit {
    characteristicId: number;

    submit: string;
    action: string;

    constructor(private route: ActivatedRoute,
                private charsListService: CharsListService,
                private location: Location) {
    };

    ngOnInit() {
        let id: any = this.route.snapshot.params['id'];
        if (id == 'addition') {
            this.action = 'addition';
        }
        else {
            this.characteristicId = id;
        }
    }

}
