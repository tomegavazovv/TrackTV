import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cast } from 'src/app/interfaces/Cast';
import { CastService } from '../cast.service';
import { Observable, mergeMap } from 'rxjs';

@Component({
    selector: 'app-cast-list',
    templateUrl: './cast-list.component.html',
    styleUrls: ['./cast-list.component.css'],
})
export class CastListComponent implements OnInit {
    @Input()
    isEnabled = false;
    castings: Cast[] = [];
    favoriteCast: Cast | undefined;
    loading: Boolean = false;
    isTvShow: Boolean = false;

    constructor(
        private castService: CastService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.loading = true;
        this.castService.favoriteCast$.subscribe(
            (fav) => (this.favoriteCast = fav)
        );

        this.route.url
            .pipe(
                mergeMap((url) => {
                    this.isTvShow = url[0].path === 'tvshows';
                    return this.loadCastData(+url[1].path);
                })
            )
            .subscribe((cast) => {
                this.castings = cast;
                this.loading = false;
            });
    }

    private loadCastData(id: Number): Observable<Cast[]> {
        if (this.isTvShow) {
            return this.loadTvShowCasts(id);
        } else {
            return this.loadMovieCasts(id);
        }
    }

    private loadTvShowCasts(favoriteCastId: Number): Observable<Cast[]> {
        this.castService.getFavoriteCastOfShow(favoriteCastId);
        return this.castService.getCastOfShow(favoriteCastId);
    }

    private loadMovieCasts(favoriteCastId: Number): Observable<Cast[]> {
        this.castService.getFavoriteCastOfMovie(favoriteCastId);
        return this.castService.getCastOfMovie(favoriteCastId);
    }

    onVoteFavorite(castId: Number) {
        const entityId = +this.route.snapshot.url[1].path;
        if (this.isTvShow) {
            this.castService.voteForFavoriteCastOfShow(entityId, castId);
        } else {
            this.castService.voteForFavoriteCastOfMovie(entityId, castId);
        }
    }
}
