import {Component, Input, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import TableModel from "../data-table/models/table.model";
import UserModel from "../../models/user.model";
import {UsersService} from "../../services/users.service";

@Component({
    selector: 'nc-users-list',
    templateUrl: 'users-list.component.html'
})
export class UsersListComponent implements OnInit {

    userModel: UserModel;

    @Input() model: TableModel = {
        data: [],
        columns: [
            {
                name: 'Name',
                key: 'firstName'
            },
            {
                name: 'Role',
                key: 'roleName',
            },
            {
                name: 'Email',
                key: 'email'
            },
            {
                name: 'Registration Date',
                key: 'registrationDate'
            }
        ]
    };

    constructor(private usersService: UsersService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.usersService.getUsers().then(users => this.model.data = users);
    }

    onSelect(user: UserModel): void {
        this.userModel = user;
    }

    /*onDelete(user: UserModel): void {
        this.usersService.deleteUser(user.id).then(() => {
            this.model.data = this.model.data.filter(getUser => getUser !== user);
            if (this.userModel === user) {
                this.userModel = null;
            }
        })
    }*/

    onDelete(): void {
        if (this.userModel) {
            this.usersService.deleteUser(this.userModel.id)
                .then(() => {
                    this.model.data = this.model.data.filter(group => group !== this.userModel);
                    this.userModel = null;
                });
        }
    }

    onEdit(user: UserModel): void {
        this.router.navigate(['/user/details', user.id]);
    }

    onCreate(): void {
        this.router.navigate(['/user/details']);
    }
}