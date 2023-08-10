import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, filter } from 'rxjs';
import { Episode } from 'src/app/interfaces/Episode';
import { TvShow } from 'src/app/interfaces/TvShow';
import { Cast } from 'src/app/interfaces/cast';
import { Comment } from 'src/app/interfaces/Comment';

@Injectable({
    providedIn: 'root',
})
export class TvshowServiceService {
    token =
        'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21lZ2F2YXpvdiIsImV4cCI6MTY5Mzk5MDAyMSwiaWF0IjoxNjkxMzk4MDIxLCJ1c2VySWQiOjEsImVtYWlsIjoidG9tZWdhdmF6b3ZAZ21haWwuY29tIn0.vrc4jz_TgDHwPgfEJT74EpvwHQsen_mAe8BF5xTyDAQ';
    episodes = new BehaviorSubject<Episode[]>([]);

    constructor(private http: HttpClient) {}

    getTvShow(tvshowId: Number): Observable<TvShow> {
        return this.http.get<TvShow>('/api/tvshows/1', {
            headers: {
                Authorization: this.token,
            },
        });
    }

    getEpisodesOfShow(tvShowId: Number, seasonNumber: Number) {
        this.http
            .get<Episode[]>(
                `/api/tvshows/episodes/${tvShowId}/${seasonNumber}`,
                {
                    headers: {
                        Authorization: this.token,
                    },
                }
            )
            .subscribe((episodes) => this.episodes.next(episodes));
    }

    addWatchedEpisode(episodeId: Number) {
        this.http
            .post<Episode>(
                `/api/tvshows/watched/${episodeId}`,
                {},
                {
                    headers: {
                        Authorization: this.token,
                    },
                }
            )
            .subscribe((updatedEp) => {
                let index = this.episodes
                    .getValue()
                    .findIndex(
                        (episode) => updatedEp.episode.id == episode.episode.id
                    );
                let updatedEpisodes = this.episodes.getValue();
                updatedEpisodes[index].watched = true;
                this.episodes.next(updatedEpisodes);
            });
    }

    unwatchEpisode(episodeId: Number) {
        this.http
            .post(
                `/api/tvshows/unwatchEpisode/${episodeId}`,
                {},
                {
                    headers: {
                        Authorization: this.token,
                    },
                }
            )
            .subscribe((_) => {
                let index = this.episodes
                    .getValue()
                    .findIndex((episode) => episodeId == episode.episode.id);

                let updatedEpisodes = this.episodes.getValue();

                updatedEpisodes[index].watched = false;

                this.episodes.next(updatedEpisodes);
            });
    }
}
