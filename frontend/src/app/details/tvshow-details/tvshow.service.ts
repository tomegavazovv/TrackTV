import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Episode } from 'src/app/interfaces/Episode';
import { TvShow } from 'src/app/interfaces/TvShow';

@Injectable({
    providedIn: 'root',
})
export class TvshowService {
    constructor(private http: HttpClient) {}

    getTvShow(tvshowId: number): Observable<TvShow> {
        return this.http.get<TvShow>(`/api/tvshows/${tvshowId}`);
    }

    getEpisodesOfShow(
        tvShowId: Number,
        seasonNumber: Number
    ): Observable<Episode[]> {
        return this.http.get<Episode[]>(
            `/api/tvshows/episodes/${tvShowId}/${seasonNumber}`
        );
    }

    addWatchedEpisode(episodeId: Number): Observable<Episode> {
        return this.http.post<Episode>(`/api/tvshows/watched/${episodeId}`, {});
    }

    unwatchEpisode(episodeId: Number): Observable<any> {
        return this.http.post(`/api/tvshows/unwatchEpisode/${episodeId}`, {});
    }

    rateEpisode(
        episodeId: Number,
        rating: Number
    ): Observable<{ averageRating: Number }> {
        return this.http.post<{ averageRating: number }>('/api/tvshows/rate', {
            episodeId,
            rating,
        });
    }

    addToMyShows(showId: Number): Observable<TvShow> {
        return this.http.post<TvShow>(`/api/tvshows/watch/${showId}`, {});
    }
}
