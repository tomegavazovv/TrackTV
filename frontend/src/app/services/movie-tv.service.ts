import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Movie } from '../interfaces/Movie';
import { map, Observable, of, switchMap } from 'rxjs';
import { MovieItem } from '../interfaces/MovieItem';
import { Cast } from '../interfaces/Cast';
import { PopularTvShow } from '../interfaces/PopularTvShow';
import { WatchedMovie } from '../interfaces/WatchedMovie';
import { SuggestedMovie } from '../interfaces/SuggestedMovie';
import { SuggestedShow } from '../interfaces/SuggestedShow';
import { WatchedShow } from '../interfaces/WatchedShow';

@Injectable({
    providedIn: 'root',
})
export class MovieTvService {
    constructor(private http: HttpClient) {}

    private getAuthorizationHeader(): HttpHeaders {
        return new HttpHeaders({
            Authorization: `${localStorage.getItem('jwtToken')}`,
        });
    }

    searchMovies(searchTerm: string): Observable<MovieItem[]> {
        return this.http.get<MovieItem[]>(
            `/api/movies/search?title=${searchTerm}`,
            {
                headers: this.getAuthorizationHeader(),
            }
        );
    }

    searchTvShows(searchTerm: string): Observable<PopularTvShow[]> {
        return this.http.get<PopularTvShow[]>(
            `/api/tvshows/search?title=${searchTerm}`,
            {
                headers: this.getAuthorizationHeader(),
            }
        );
    }

    getWatchedMovies(): Observable<WatchedMovie[]> {
        return this.http.get<any[]>('/api/movies/watched', {
            headers: this.getAuthorizationHeader(),
        });
        // .pipe(map((data) => data.map((item) => item.movie)));
    }

    getWatchedShows(): Observable<WatchedShow[]> {
        return this.http.get<any[]>('/api/tvshows/watched', {
            headers: this.getAuthorizationHeader(),
        });
    }

    logMovie(
        movie: Movie,
        rating: Number,
        review: String,
        favoriteCast: Cast
    ): Observable<any[]> {
        const headers = this.getAuthorizationHeader();

        const watchedRequest = this.http.post<any[]>(
            `/api/movies/addWatched/${movie.data.id}`,
            {},
            { headers }
        );

        return watchedRequest.pipe(
            switchMap((watchedResponse: any) => {
                const rateBody = {
                    movieId: movie.data.id,
                    rating: rating,
                    comment: review,
                };

                const rateRequest = this.http.post<any[]>(
                    `/api/movies/rate`,
                    rateBody,
                    { headers }
                );

                return rateRequest.pipe(
                    switchMap((rateResponse: any) => {
                        const castBody = {
                            id: movie.data.id,
                            castId: favoriteCast.id,
                        };

                        const castRequest = this.http.post<any[]>(
                            `/api/movies/favoriteCast`,
                            castBody,
                            { headers }
                        );

                        return castRequest.pipe(
                            switchMap((castResponse: any) => {
                                return of([
                                    watchedResponse,
                                    rateResponse,
                                    castResponse,
                                ]);
                            })
                        );
                    })
                );
            })
        );
    }

    getSuggestedMovies(): Observable<SuggestedMovie[]> {
        return this.http.get<SuggestedMovie[]>('/api/suggestedMovies', {
            headers: this.getAuthorizationHeader(),
        });
    }

    getSuggestedShows(): Observable<SuggestedShow[]> {
        return this.http.get<SuggestedShow[]>('/api/suggestedShows', {
            headers: this.getAuthorizationHeader(),
        });
    }

    deleteSuggestion(id: number) {
        this.http.delete(`/api/suggestion/${id}`).subscribe();
    }
}
