import {Component, OnInit} from "@angular/core";
import UserModel from "../../models/user.model";
import {ActivatedRoute} from "@angular/router";
import {UsersService} from "../../services/users.service";

@Component({
    selector: 'nc-user-details',
    templateUrl: 'user-details.component.html'
})
export class UserDetailsComponent implements OnInit {
    userId: number;

    constructor(
        private usersService: UsersService,
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void{
        this.route.params.subscribe(params => {this.userId = +params['id'];});
    }
}