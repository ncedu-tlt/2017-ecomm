import {Component, Input, OnInit} from "@angular/core";
import {UsersService} from "../../services/users.service";
import ReviewModel from "../../models/review.model";

@Component({
    selector: 'nc-user-reviews',
    templateUrl: 'user-reviews.component.html',
    styleUrls: ['user-reviews.component.css']
})

export class UserReviewsComponent implements OnInit {

    @Input()
    userId: number;

    reviews: ReviewModel[];
    private productId: number = 0;

    constructor(private usersService: UsersService) {
    }

    ngOnInit(): void {
        this.usersService.getReviewsByUser(this.userId).then(reviews => this.reviews = reviews);
    }

    onDelete(value: number): void {
        this.productId = value;
        this.usersService.deleteReview(this.userId, this.productId)
            .then(this.ngOnInit.bind(this));
    }
}
