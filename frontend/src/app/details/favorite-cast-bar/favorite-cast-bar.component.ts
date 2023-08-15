import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, UrlSegment } from '@angular/router';
import { Cast } from 'src/app/interfaces/Cast';
import { CastService } from '../cast.service';
import { TopCast } from 'src/app/interfaces/TopCast';
import { Observable, filter } from 'rxjs';
import { switchMap } from 'rxjs/operators';

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
        this.route.url
            .pipe(
                filter(
                    (url) => url[0].path == 'movies' || url[0].path == 'tvshows'
                ),
                switchMap((url) => this.loadCast(url))
            )
            .subscribe((topCast) => (this.topCastings = topCast));

        this.castService.favoriteCast$.subscribe((favCast) => {
            const prevCast = this.favoriteCast;
            this.favoriteCast = favCast;

            if (this.topCastings && prevCast) {
                const indexToIncrease = this.topCastings.findIndex(
                    (cast) => cast.name === favCast.name
                );
                const indexToDecrease = this.topCastings.findIndex(
                    (cast) => cast.name === prevCast.name
                );

                if (indexToIncrease !== -1) {
                    this.topCastings[indexToIncrease].count =
                        this.topCastings[indexToIncrease].count.valueOf() + 1;
                } else {
                    this.topCastings = [
                        ...this.topCastings,
                        {
                            id: favCast.id,
                            role: favCast.role,
                            name: favCast.name,
                            imageUrl: favCast.imageUrl,
                            count: 1,
                        },
                    ];
                }

                if (indexToDecrease !== -1) {
                    console.log(indexToDecrease);
                    this.topCastings[indexToDecrease].count =
                        this.topCastings[indexToDecrease].count.valueOf() - 1;
                }
            }
        });
    }

    private loadCast(url: UrlSegment[]): Observable<TopCast[]> {
        const id = +url[1].path;
        if (url[0].path == 'movies') {
            return this.loadTopCastOfMovie(id);
        } else {
            return this.loadTopCastOfShow(id);
        }
    }

    private loadTopCastOfMovie(movieId: number): Observable<TopCast[]> {
        return this.castService.getTopCastOfMovie(movieId);
    }

    private loadTopCastOfShow(showId: number): Observable<TopCast[]> {
        return this.castService.getTopCastOfShow(showId);
    }
}
