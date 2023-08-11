import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { Cast } from '../interfaces/Cast';

@Injectable({
    providedIn: 'root',
})
export class CastService {
    token =
        'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21lZ2F2YXpvdiIsImV4cCI6MTY5Mzk5MDAyMSwiaWF0IjoxNjkxMzk4MDIxLCJ1c2VySWQiOjEsImVtYWlsIjoidG9tZWdhdmF6b3ZAZ21haWwuY29tIn0.vrc4jz_TgDHwPgfEJT74EpvwHQsen_mAe8BF5xTyDAQ';
    favoriteCast = new Subject<Cast>();
    castings = new Subject<Cast[]>();

    constructor(private http: HttpClient, private route: ActivatedRoute) {}

    voteForFavoriteCastOfShow(showId: Number, castId: Number) {
        this.http
            .post<any>(
                '/api/tvshows/favoriteCast',
                {
                    castId: castId,
                    id: showId,
                },
                {
                    headers: {
                        Authorization: this.token,
                    },
                }
            )
            .subscribe((favCast) => this.favoriteCast.next(favCast));
    }

    getFavoriteCastOfShow(showId: Number) {
        this.http
            .get<Cast>(`/api/tvshows/favoriteCast/${showId}`, {
                headers: {
                    Authorization: this.token,
                },
            })
            .subscribe((favCast) => this.favoriteCast.next(favCast));
    }

    getCastOfShow(tvShowId: Number) {
        this.http
            .get<Cast[]>('/api/tvshows/cast/1', {
                headers: {
                    Authorization: this.token,
                },
            })
            .subscribe((castings) => this.castings.next(castings));
    }

    getTopCastOfMovie(movieId: Number): Observable<Cast[]> {
        return this.http.get<Cast[]>('/api/movies/topCast/1');
    }

    getTopCastOfShow(showId: Number): Observable<Cast[]> {
        return this.http.get<Cast[]>('/api/tvshows/topCast/1');
    }

    voteForFavoriteCastOfMovie(movieId: Number, castId: Number) {
        this.http
            .post<any>(
                '/api/movies/favoriteCast',
                {
                    castId: castId,
                    id: movieId,
                },
                {
                    headers: {
                        Authorization: this.token,
                    },
                }
            )
            .subscribe((favCast) => this.favoriteCast.next(favCast));
    }

    getFavoriteCastOfMovie(movieId: Number) {
        this.http
            .get<Cast>(`/api/movies/favoriteCast/${movieId}`, {
                headers: {
                    Authorization: this.token,
                },
            })
            .subscribe((favCast) => {
                this.favoriteCast.next(favCast);
            });
    }

    getCastOfMovie(movieId: Number) {
        this.http
            .get<Cast[]>('/api/movies/cast/1', {
                headers: {
                    Authorization: this.token,
                },
            })
            .subscribe((castings) => {
                this.castings.next(castings);
            });
    }
}
