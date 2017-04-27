import "rxjs/add/operator/switchMap";
import {Component, Input, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";
import UserModel from "../../models/user.model";
import {UsersService} from "../../services/users.service";
import {LocationStrategy} from "@angular/common";
import {CharGroupService} from "../../services/char-group.service";
import RoleModel from "../../models/role.model";

@Component({
    selector: 'nc-user-editor',
    templateUrl: './user-editor.component.html'
})


export class UserEditorComponent implements OnInit {

    user: UserModel = new UserModel();
    role = new RoleModel;


    /*user: UserModel = new UserModel();*/
    @Input() userId: number;

    constructor(
        private usersService: UsersService,
        private charGroupService: CharGroupService,
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void {
        this.user.role = this.role;
        if (this.userId){
            this.usersService.getUser(this.userId).then(user => this.user = user);
            console.log(+this.userId);
            console.log(this.user);
        }else {
            console.log('New User');
        }
    }

    onCreate(): void {
        this.usersService.addUser(this.user);
    }

    onEdit(): void {
        this.usersService.updateUser(this.user);
    }
}