import { Component, Input, OnInit } from '@angular/core';
import { Episode } from 'src/app/interfaces/Episode';
import { TvshowServiceService } from '../tvshow-service.service';

@Component({
    selector: 'app-episode-list',
    templateUrl: './episode-list.component.html',
    styleUrls: ['./episode-list.component.css'],
})
export class EpisodeListComponent implements OnInit {
    @Input()
    showId: Number | undefined;
    episodes: Episode[] = [];

    constructor(private tvShowService: TvshowServiceService) {}

    ngOnInit() {
        this.tvShowService.getEpisodesOfShow(1, 1);

        this.tvShowService.episodes.subscribe(
            (episodes) => (this.episodes = episodes)
        );
    }

    onClickEpisode(episodeId: Number, watched: Boolean) {
        console.log(watched);
        if (watched) {
            this.tvShowService.unwatchEpisode(episodeId);
        } else {
            this.tvShowService.addWatchedEpisode(episodeId);
        }
    }
}
