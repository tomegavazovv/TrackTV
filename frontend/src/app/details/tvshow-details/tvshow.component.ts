import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { filter, map, switchMap, take } from 'rxjs/operators';
import { TvShow } from 'src/app/interfaces/TvShow';
import { Cast } from 'src/app/interfaces/Cast';
import { CastService } from '../cast.service';
import { CommentsService } from '../comments.service';
import { TvshowService } from './tvshow.service';

@Component({
    selector: 'app-tvshow',
    templateUrl: './tvshow.component.html',
    styleUrls: ['./tvshow.component.css'],
})
export class TvshowComponent implements OnInit {
    tvShow: TvShow | undefined;
    favoriteCast: Cast | undefined;
    loading = false;

    constructor(
        private tvShowService: TvshowService,
        private castService: CastService,
        private commentsService: CommentsService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.loading = true;
        this.route.paramMap
            .pipe(
                take(1),
                filter((paramMap) => paramMap.has('id')),
                map((paramMap) => +paramMap.get('id')!),
                switchMap((id) => {
                    return this.tvShowService.getTvShow(id);
                })
            )
            .subscribe((tvShow) => {
                this.tvShow = tvShow;
                this.initializeData(tvShow.data.id);
                this.loading = false;
            });

        this.castService.favoriteCast$.subscribe(
            (favCast) => (this.favoriteCast = favCast)
        );
    }

    private initializeData(tvShowId: Number) {
        this.castService.getCastOfShow(tvShowId);
        this.castService.getFavoriteCastOfShow(tvShowId);
        this.commentsService.getCommentsOfShow(tvShowId);
        this.tvShowService.getEpisodesOfShow(tvShowId, 1);
    }

    onVoteFavorite(castId: Number) {
        if (this.tvShow) {
            this.castService.voteForFavoriteCastOfShow(
                this.tvShow.data.id,
                castId
            );
        }
    }

    onNewComment(text: String) {
        if (this.tvShow) {
            this.commentsService.addCommentToShow(this.tvShow.data.id, text);
        }
    }

    onSeasonChange(event: any) {
        this.tvShowService.getEpisodesOfShow(1, event.target.value);
    }

    onAddToMyShows() {
        if (this.tvShow) {
            if (!this.tvShow.watched) {
                this.tvShowService
                    .addToMyShows(this.tvShow.data.id)
                    .subscribe((show) => (this.tvShow = show));
            }
        }
    }
}
