import {Component, OnInit} from "@angular/core";
import {CharsListService} from "../../services/charsList.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {FormControl} from "@angular/forms";
import CharacteristicModel from "../../models/characteristic.model";
import CharGroupModel from "../../models/char-group.model";
import {CharGroupService} from "../../services/char-group.service";

@Component({
    selector: 'nc-char-editor',
    templateUrl: 'char-editor.component.html'
})
export class CharEditorComponent implements OnInit {
    characteristicId: number;
    selectedCharGroupId: number;
    selectedCategoryId: number;
    submit: string;
    action: string;

    filterable: FormControl = new FormControl(false);
    characteristic: CharacteristicModel = new CharacteristicModel();
    charGroups: CharGroupModel[];

    constructor(private route: ActivatedRoute,
                private charsListService: CharsListService,
                private router: Router,
                private charGroupService: CharGroupService,
                private location: Location) {
    };

    ngOnInit() {
        this.route.queryParams.subscribe(params => {
            this.selectedCategoryId = +params['categoryId'];
            this.characteristicId = +params['characteristicId'];
            this.action = params['action'];
            if (this.characteristicId != 0) {
                this.charsListService.get(this.characteristicId)
                    .then((characteristic) => {
                        if (this.action == 'edit')
                            this.characteristic = characteristic
                    });
            }
        });
        this.charGroupService.getAll()
            .then((groups) => this.charGroups = groups);
    }

    onSubmit() {
        if (this.action == 'addition') {
            this.addition();
        }
        if (this.action == 'edit') {
            this.edit();
        }
    }

    addition(): void {
        if (!this.characteristic.characteristicName.trim()) return;
        this.characteristic.characteristicGroupId = this.selectedCharGroupId;
        this.characteristic.categoryId = this.selectedCategoryId;
        this.charsListService.add(this.characteristic)
            .then(() => this.back())
            .catch(() => this.submit = 'error')
    }

    edit(): void {
        if(this.characteristic){
            this.characteristic.characteristicGroupId = this.selectedCharGroupId;
            this.characteristic.categoryId = this.selectedCategoryId;
            this.charsListService.update(this.characteristic)
                .then(() => this.back())
                .catch(() => this.submit = 'error')
        }
    }

    charGroupRedirect(): void {
        this.router.navigate(['/char-group-list']);
    }

    back(): void {
        this.location.back();
    }
}
