import "rxjs/add/operator/switchMap";
import {Component, Input, OnInit} from "@angular/core";
import UserModel from "../../models/user.model";
import {UsersService} from "../../services/users.service";
import {Location} from "@angular/common";
import RoleModel from "../../models/role.model";
import {FormGroup} from "@angular/forms";

declare const contextPath: string;

@Component({
    selector: 'nc-user-editor',
    templateUrl: 'user-editor.component.html',
    styleUrls: ['user-editor.component.css']
})

export class UserEditorComponent implements OnInit {

    @Input()
    userId: number;

    user: UserModel = new UserModel();
    roles: RoleModel[];
    pathToUserAvatar = contextPath;
    isSent: boolean = false;
    isError: boolean = false;


    constructor(private usersService: UsersService,
                private location: Location,
    ) {
    }

    ngOnInit(): void {
        this.user.role = new RoleModel;
        this.usersService.getRoles().then(roles => this.roles = roles);
        if (this.userId) {
            this.usersService.getUser(this.userId).then(user => this.user = user);
        }
    }

    onSave(form: FormGroup): void {
        if (this.userId) {
            this.usersService.updateUser(this.user)
                .then(() => {
                    this.isSent = true;
                    this.isError = false;
                    this.location.back();
                })
                .catch(() => {
                    this.isError = true;
                    this.isSent = false
                });
        } else {
            this.usersService.addUser(this.user)
                .then(() => {
                    this.isSent = true;
                    this.isError = false;
                    form.reset();
                })
                .catch(() => {
                    this.isError = true;
                    this.isSent = false
                });
        }
    }

    onCancel(): void {
        this.location.back();
    }

    onDelete(): void {
        if (this.userId) {
            this.usersService.deleteUser(this.userId).then(this.onCancel.bind(this));
        }
    }
}