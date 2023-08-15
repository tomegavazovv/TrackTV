import { Component, Input, OnInit } from '@angular/core';
import { Episode } from 'src/app/interfaces/Episode';
import { TvshowService } from '../tvshow.service';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-episode-list',
    templateUrl: './episode-list.component.html',
    styleUrls: ['./episode-list.component.css'],
})
export class EpisodeListComponent implements OnInit {
    episodes: Episode[] = [];
    loading: Boolean = false;

    constructor(
        private tvShowService: TvshowService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.loading = true;
        this.route.url.subscribe((url) => {
            this.tvShowService
                .getEpisodesOfShow(+url[1].path, 1)
                .subscribe((episodes) => {
                    this.episodes = episodes;
                    this.loading = false;
                });
        });
    }

    onClickEpisode(episodeId: Number, watched: Boolean) {
        if (watched) {
            this.tvShowService.unwatchEpisode(episodeId).subscribe(() => {
                const updatedEpisodes = this.episodes.map((ep) =>
                    ep.episode.id === episodeId ? { ...ep, watched: false } : ep
                );
                this.episodes = updatedEpisodes;
            });
        } else {
            this.tvShowService
                .addWatchedEpisode(episodeId)
                .subscribe((updatedEp) => {
                    const updatedEpisodes = this.episodes.map((ep) =>
                        ep.episode.id === updatedEp.episode.id
                            ? { ...ep, watched: true }
                            : ep
                    );
                    this.episodes = updatedEpisodes;
                });
        }
    }

    onRatingChange(episodeId: Number, rating: Number) {
        this.tvShowService
            .rateEpisode(episodeId, rating)
            .subscribe((averageRating) => {
                let index = this.episodes.findIndex(
                    (ep) => (ep.episode.id = episodeId)
                );
                let updatedEpisodes = [...this.episodes];
                updatedEpisodes[index] = {
                    ...this.episodes[index],
                    rating: rating,
                    averageRating: averageRating.averageRating.valueOf(),
                };
                this.episodes = updatedEpisodes;
            });
    }
}
