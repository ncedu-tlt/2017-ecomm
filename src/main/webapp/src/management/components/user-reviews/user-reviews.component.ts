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

    private reviews: ReviewModel[];

    constructor(private usersService: UsersService) {
    }

    ngOnInit(): void {
        this.loadReview();
    }

    onDelete(productId: number): void {
        this.usersService.deleteReview(this.userId, productId)
            .then(this.loadReview.bind(this));
    }

    loadReview(): void {
        this.usersService.getReviewsByUserId(this.userId).then(reviews => this.reviews = reviews);
    }
}
