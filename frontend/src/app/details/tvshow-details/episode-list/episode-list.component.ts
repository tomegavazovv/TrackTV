import { Component, Input, OnInit } from '@angular/core';
import { Episode } from 'src/app/interfaces/Episode';
import { TvshowService } from '../tvshow.service';
import { ActivatedRoute } from '@angular/router';
import { filter, map, switchMap, take, tap } from 'rxjs';

@Component({
    selector: 'app-episode-list',
    templateUrl: './episode-list.component.html',
    styleUrls: ['./episode-list.component.css'],
})
export class EpisodeListComponent implements OnInit {
    episodes: Episode[] = [];
    loading = false;

    constructor(
        private tvShowService: TvshowService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.route.paramMap
            .pipe(
                take(1),
                filter((paramMap) => paramMap.has('id')),
                map((paramMap) => +paramMap.get('id')!),
                switchMap((id) =>
                    this.tvShowService.getEpisodesOfShowObv(id, 1)
                ),
                tap((episodes) => (this.episodes = episodes))
            )
            .subscribe();

        this.tvShowService.episodes$.asObservable().subscribe({
            next: (episodes) => (this.episodes = episodes),
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
                    (ep) => ep.episode.id == episodeId
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
