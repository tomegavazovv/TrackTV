import { Component, OnInit } from '@angular/core';
import { TvShow } from 'src/app/interfaces/TvShow';
import { TvshowServiceService } from './tvshow-service.service';

import { Cast } from 'src/app/interfaces/Cast';
import { CastService } from '../cast.service';
import { CommentsService } from '../comments.service';

@Component({
    selector: 'app-tvshow',
    templateUrl: './tvshow.component.html',
    styleUrls: ['./tvshow.component.css'],
})
export class TvshowComponent implements OnInit {
    tvShow: TvShow | undefined;
    favoriteCast: Cast | undefined;
    watchedEpisodesNumber = 0;

    constructor(
        private tvShowService: TvshowServiceService,
        private castService: CastService,
        private commentsService: CommentsService
    ) {}

    ngOnInit() {
        this.tvShowService.getTvShow(1).subscribe((tvShow) => {
            this.tvShow = tvShow;
            this.castService.getCastOfShow(1);
            this.castService.getFavoriteCastOfShow(1);
            this.commentsService.getCommentsOfShow(1);
            this.tvShowService.getEpisodesOfShow(1, 1);
        });

        this.castService.favoriteCast.subscribe(
            (favCast) => (this.favoriteCast = favCast)
        );

        this.tvShowService.episodes.subscribe((episodes) => {
            this.watchedEpisodesNumber = episodes.filter(
                (e) => e.watched
            ).length;
        });
    }

    onVoteFavorite(castId: Number) {
        if (this.tvShow) {
            this.castService.voteForFavoriteCastOfShow(1, castId);
        }
    }

    onNewComment(text: String) {
        if (this.tvShow) {
            this.commentsService.addCommentToShow(1, text);
        }
    }
}
