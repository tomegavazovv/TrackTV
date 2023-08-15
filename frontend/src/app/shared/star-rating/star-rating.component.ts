import {
    Component,
    EventEmitter,
    Input,
    OnChanges,
    OnInit,
    Output,
    SimpleChanges,
} from '@angular/core';

@Component({
    selector: 'app-star-rating',
    templateUrl: './star-rating.component.html',
    styleUrls: ['./star-rating.component.sass'],
})
export class StarRatingComponent implements OnChanges {
    @Input()
    selectedRating: Number = 0;
    @Input()
    isEnabled: Boolean = true;
    showTooltip = false;
    stars = [
        {
            id: 1,
            icon: 'star',
            class: 'star-gray star-hover star',
        },
        {
            id: 2,
            icon: 'star',
            class: 'star-gray star-hover star',
        },
        {
            id: 3,
            icon: 'star',
            class: 'star-gray star-hover star',
        },
        {
            id: 4,
            icon: 'star',
            class: 'star-gray star-hover star',
        },
        {
            id: 5,
            icon: 'star',
            class: 'star-gray star-hover star',
        },
    ];

    constructor() {}

    // ngOnInit(): void {
    //     this.stars.forEach((star, index) => {
    //         if (this.selectedRating == 0) {
    //         } else if (index < this.selectedRating.valueOf()) {
    //             star.class = 'star-gold star';
    //         } else {
    //             star.class = 'star-gray star';
    //         }
    //     });
    // }

    ngOnChanges(changes: SimpleChanges): void {
        if (changes['selectedRating']) {
            this.updateStarClasses();
        }
    }

    updateStarClasses(): void {
        this.stars.forEach((star, index) => {
            if (this.selectedRating === 0) {
                star.class = 'star-gray star-hover star';
            } else if (index < this.selectedRating.valueOf()) {
                star.class = 'star-gold star';
            } else {
                star.class = 'star-gray star';
            }
        });
    }

    @Output() ratingChanged: EventEmitter<number> = new EventEmitter<number>();

    selectStar(value: number): void {
        if (this.isEnabled) {
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
}
