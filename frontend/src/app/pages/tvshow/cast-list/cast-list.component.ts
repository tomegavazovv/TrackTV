import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Cast } from 'src/app/interfaces/Cast';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { CastService } from '../../cast.service';

@Component({
    selector: 'app-cast-list',
    templateUrl: './cast-list.component.html',
    styleUrls: ['./cast-list.component.css'],
})
export class CastListComponent implements OnInit {
    castings: Cast[] = [];
    favoriteCast: Cast | undefined;

    constructor(
        private castService: CastService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.route.url.subscribe((url) => {
            if (url[0].path == 'tvshows') {
                this.castService.favoriteCast.subscribe(
                    (fav) => (this.favoriteCast = fav)
                );
                this.castService.castings.subscribe(
                    (castings) => (this.castings = castings)
                );
            } else {
                this.castService.favoriteCast.subscribe(
                    (fav) => (this.favoriteCast = fav)
                );
                this.castService.castings.subscribe(
                    (castings) => (this.castings = castings)
                );
            }
        });
    }

    onVoteFavorite(castId: Number) {
        let urls = this.route.snapshot.url;
        if (urls[0].path == 'movies') {
            this.castService.voteForFavoriteCastOfMovie(+urls[1].path, castId);
        } else {
            this.castService.voteForFavoriteCastOfShow(+urls[1].path, castId);
        }
    }
}
