import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import TableModel from "../data-table/models/table.model";
import UserModel from "../../models/user.model";
import {UsersService} from "../../services/users.service";

@Component({
    selector: 'nc-users-list',
    templateUrl: 'users-list.component.html'
})
export class UsersListComponent implements OnInit {

    model: TableModel = {
        data: [],
        columns: [
            {
                name: 'ID',
                key: 'id'
            },
            {
                name: 'Email',
                key: 'email'
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
        this.router.navigate(['/user', user.id]);
    }

}