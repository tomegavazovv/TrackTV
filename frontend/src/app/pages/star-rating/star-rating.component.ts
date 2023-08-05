import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
    selector: 'app-star-rating',
    templateUrl: './star-rating.component.html',
    styleUrls: ['./star-rating.component.sass']
})
export class StarRatingComponent implements OnInit {


    selectedRating = 0;
    stars = [
        {
            id: 1,
            icon: 'star',
            class: 'star-gray star-hover star'
        },
        {
            id: 2,
            icon: 'star',
            class: 'star-gray star-hover star'
        },
        {
            id: 3,
            icon: 'star',
            class: 'star-gray star-hover star'
        },
        {
            id: 4,
            icon: 'star',
            class: 'star-gray star-hover star'
        },
        {
            id: 5,
            icon: 'star',
            class: 'star-gray star-hover star'
        }

    ];

    constructor() {
    }

    ngOnInit(): void {
    }

    @Output() ratingChanged: EventEmitter<number> = new EventEmitter<number>();


    selectStar(value: number): void {
        this.stars.forEach((star) => {
            if (star.id <= value) {
                star.class = 'star-gold star';
            } else {
                star.class = 'star-gray star';
            }
        });
        this.selectedRating = value;
        this.ratingChanged.emit(value);
    }

}
