import {Component, Input, Output, EventEmitter} from '@angular/core';
import {CharGroupService} from "../../services/char-group.service";
import CharGroupModel from "../../models/char-group.model";

@Component({
    selector: 'nc-char-group-editor',
    templateUrl: 'char-group-editor.component.html',
    styleUrls: ['char-group-editor.component.css']
})
export class CharGroupEditorComponent{

    model: string;

    @Output('addGroup') addGroup = new EventEmitter<String>();
    @Output('deleteGroup') deleteGroup = new EventEmitter<any>();

    onAddition(): void{
        this.addGroup.emit(this.model);
    }

    onDelete(): void{
        this.deleteGroup.emit();
    }

    showPopup(event: any, myPopup: any):void{
        myPopup.show(event, {position: 'top center'})
    }

    hidePopup(myPopup: any):void{
        myPopup.hide()
    }
}
