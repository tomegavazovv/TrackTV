import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
// import { Movie } from 'src/app/models/movie';
import {Movie} from "../../interfaces/movie";
import { Cast } from 'src/app/interfaces/cast';
import { Comment } from 'src/app/interfaces/Comment';
import { ActivatedRoute } from '@angular/router';

@Injectable({
    providedIn: 'root',
})
export class MovieService {
    token =
        'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21lZ2F2YXpvdiIsImV4cCI6MTY5Mzk5MDAyMSwiaWF0IjoxNjkxMzk4MDIxLCJ1c2VySWQiOjEsImVtYWlsIjoidG9tZWdhdmF6b3ZAZ21haWwuY29tIn0.vrc4jz_TgDHwPgfEJT74EpvwHQsen_mAe8BF5xTyDAQ';

    constructor(private http: HttpClient, private route: ActivatedRoute) {}

    getMovieById(movieId: Number): Observable<Movie> {
        return this.http.get<Movie>('/api/movies/1', {
            headers: {
                Authorization: this.token,
            },
        });
    }

    markMovieAsWatched(movieId: Number) {
        this.http
            .post(
                `/api/movies/addWatched/${movieId}`,
                {},
                {
                    headers: {
                        Authorization: this.token,
                    },
                }
            )
            .subscribe();
    }

    markMovieAsUnwatched(movieId: Number) {
        this.http
            .post(
                `/api/movies/unwatch/${movieId}`,
                {},
                {
                    headers: {
                        Authorization: this.token,
                    },
                }
            )
            .subscribe();
    }
}
