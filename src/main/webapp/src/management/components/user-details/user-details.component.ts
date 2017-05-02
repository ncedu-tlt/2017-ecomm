import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'nc-user-details',
    templateUrl: 'user-details.component.html'
})
export class UserDetailsComponent implements OnInit {
    userId: number;

    constructor(
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void{
        this.route.params.subscribe(params => {this.userId = +params['id'];});
    }
}