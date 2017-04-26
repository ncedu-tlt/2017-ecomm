import {Component} from "@angular/core";
import UserModel from "../../models/user.model";

@Component({
    selector: 'nc-user-details',
    templateUrl: 'user-details.component.html'
})
export class UserDetailsComponent {
    user: UserModel;
}