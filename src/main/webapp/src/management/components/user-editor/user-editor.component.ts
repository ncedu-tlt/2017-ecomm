import "rxjs/add/operator/switchMap";
import {Component, Input, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";
import UserModel from "../../models/user.model";
import {UsersService} from "../../services/users.service";
import {Location} from "@angular/common";
import {CharGroupService} from "../../services/char-group.service";
import RoleModel from "../../models/role.model";

@Component({
    selector: 'nc-user-editor',
    templateUrl: './user-editor.component.html'
})

export class UserEditorComponent implements OnInit {

    user: UserModel = new UserModel();
    role = new RoleModel;
    pathToUserAvatar = `/ecomm`;
    @Input() userId: number;
    isSent: boolean = false;
    isError: boolean = false;

    constructor(
        private usersService: UsersService,
        private location: Location,
        private charGroupService: CharGroupService,
        private route: ActivatedRoute,
    ) { }

    ngOnInit(): void {
        this.user.role = this.role;
        if (this.userId){
            this.usersService.getUser(this.userId).then(user => this.user = user);
        }else {
        }
    }

    onSave(): void {
        if (this.userId){
            this.usersService.updateUser(this.user)
                .then(() => {this.isSent = true; this.isError = false})
                .catch(() => {this.isError = true; this.isSent = false});
        } else {
            this.usersService.addUser(this.user)
                .then(() => {this.isSent = true; this.isError = false})
                .catch(() => {this.isError = true; this.isSent = false});
        }
    }

    onCancel(): void {
        this.location.back();
    }

    onDelete(): void{
        if (this.userId){
            this.usersService.deleteUser(this.userId).then(() => this.onCancel());
        }
    }
}