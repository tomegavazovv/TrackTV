import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Cast } from '../interfaces/Cast';
import { TopCast } from '../interfaces/TopCast';

@Injectable({
    providedIn: 'root',
})
export class CastService {
    private favoriteCastSubject = new Subject<Cast>();
    favoriteCast$ = this.favoriteCastSubject.asObservable();

    constructor(private http: HttpClient) {}

    voteForFavoriteCastOfShow(showId: Number, castId: Number): void {
        this.http
            .post<Cast>('/api/tvshows/favoriteCast', {
                castId,
                id: showId,
            })
            .subscribe((favCast) => this.favoriteCastSubject.next(favCast));
    }

    getFavoriteCastOfShow(showId: Number): void {
        this.http
            .get<Cast>(`/api/tvshows/favoriteCast/${showId}`)
            .subscribe((favCast) => this.favoriteCastSubject.next(favCast));
    }

    getCastOfShow(tvShowId: Number): Observable<Cast[]> {
        return this.http.get<Cast[]>(`/api/tvshows/cast/${tvShowId}`);
    }

    getTopCastOfMovie(movieId: Number): Observable<TopCast[]> {
        return this.http.get<TopCast[]>(`/api/movies/topCast/${movieId}`);
    }

    getTopCastOfShow(showId: Number): Observable<TopCast[]> {
        return this.http.get<TopCast[]>(`/api/tvshows/topCast/${showId}`);
    }

    voteForFavoriteCastOfMovie(movieId: Number, castId: Number): void {
        this.http
            .post<Cast>('/api/movies/favoriteCast', {
                castId,
                id: movieId,
            })
            .subscribe((favCast) => this.favoriteCastSubject.next(favCast));
    }

    getFavoriteCastOfMovie(movieId: Number): void {
        this.http
            .get<Cast>(`/api/movies/favoriteCast/${movieId}`)
            .subscribe((favCast) => {
                this.favoriteCastSubject.next(favCast);
            });
    }

    getCastOfMovie(movieId: Number): Observable<Cast[]> {
        return this.http.get<Cast[]>(`/api/movies/cast/${movieId}`);
    }
}
