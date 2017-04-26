import "rxjs/add/operator/switchMap";
import {Component, Input, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";
import UserModel from "../../models/user.model";
import {UsersService} from "../../services/users.service";
import {LocationStrategy} from "@angular/common";

@Component({
    selector: 'nc-user-editor',
    templateUrl: './user-editor.component.html'
})


export class UserEditorComponent implements OnInit {

    @Input() user: UserModel;

    constructor(
        private usersService: UsersService,
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void {
        console.log(this.user);
        this.route.params
            .switchMap((params: Params) => this.usersService.getUser(+params['id']))
            .subscribe(user => this.user = user);
        if (this.user){
            console.log('Id is empty')
        }
        /*if (this.user.id != null){

        /*}else {
            console.log('Id is Empty')
        }*!/*/

        /*this.route.params
            .switchMap((params: Params) => (+params['id']))*/
    }
}