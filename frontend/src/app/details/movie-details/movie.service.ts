import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Movie } from '../../interfaces/movie';

@Injectable({
    providedIn: 'root',
})
export class MovieService {
    averageRating$ = new Subject<Number>();

    constructor(private http: HttpClient) {}

    getMovieById(movieId: Number): Observable<Movie> {
        return this.http.get<Movie>(`/api/movies/${movieId}`);
    }

    markMovieAsWatched(movieId: Number) {
        this.http.post(`/api/movies/addWatched/${movieId}`, {}).subscribe();
    }

    markMovieAsUnwatched(movieId: Number) {
        this.http.post(`/api/movies/unwatch/${movieId}`, {}).subscribe();
    }

    rateMovie(movieId: Number, rating: Number) {
        this.http
            .post('/api/movies/rate', {
                movieId,
                rating,
            })
            .subscribe((_) => {
                this.getAverageRating(movieId);
            });
    }

    getAverageRating(movieId: Number) {
        this.http
            .get<Number>(`/api/movies/averageRating/${movieId}`)
            .subscribe((avg) => this.averageRating$.next(avg));
    }
}
