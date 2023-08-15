import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cast } from 'src/app/interfaces/Cast';
import { CastService } from '../cast.service';
import { TopCast } from 'src/app/interfaces/TopCast';

@Component({
    selector: 'app-favorite-cast-bar',
    templateUrl: './favorite-cast-bar.component.html',
    styleUrls: ['./favorite-cast-bar.component.css'],
})
export class FavoriteCastBarComponent implements OnInit {
    topCastings: TopCast[] = [];
    favoriteCast: Cast | undefined;
    loading = false;

    constructor(
        private route: ActivatedRoute,
        private castService: CastService
    ) {}

    ngOnInit() {
        this.route.url.subscribe((url) => {
            this.loading = true;
            const id = +url[1].path;
            if (url[0].path == 'movies') {
                this.loadTopCastOfMovie(id);
            } else {
                this.loadTopCastOfShow(id);
            }
        });

        this.castService.favoriteCast$.subscribe(
            (favCast) => (this.favoriteCast = favCast)
        );
    }

    private loadTopCastOfMovie(movieId: number) {
        this.castService.getTopCastOfMovie(movieId).subscribe((top) => {
            this.topCastings = top;
            this.loading = false;
        });
    }

    private loadTopCastOfShow(showId: number) {
        this.castService.getTopCastOfShow(showId).subscribe((top) => {
            this.topCastings = top;
            this.loading = false;
        });
    }
}
