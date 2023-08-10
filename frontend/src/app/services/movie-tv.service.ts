import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Movie} from "../interfaces/movie";
import {TvShow} from "../interfaces/TvShow";
import {map, Observable, of, switchMap} from "rxjs";
import {Cast} from "../interfaces/cast";

@Injectable({
    providedIn: 'root'
})
export class MovieTvService {

    constructor(private http: HttpClient) {
    }

    private getAuthorizationHeader(): HttpHeaders {
        return new HttpHeaders({
            'Authorization': `${localStorage.getItem('jwtToken')}`
        });
    }

    searchMovies(searchTerm: string): Observable<Movie[]> {
        return this.http.get<Movie[]>(`/api/movies/search?title=${searchTerm}`, {headers: this.getAuthorizationHeader()});
    }

    getWatchedShows(): Observable<TvShow[]> {
        return this.http.get<any[]>('/api/tvshows/watched', {headers: this.getAuthorizationHeader()}).pipe(
            map(data => data.map(item => item.show))
        );
    }

    logMovie(movie: Movie, rating: Number, review: String, favoriteCast: Cast): Observable<any[]> {
        const headers = this.getAuthorizationHeader();

        const watchedRequest = this.http.post<any[]>(`/api/movies/addWatched/${movie.data.id}`, {}, {headers});

        return watchedRequest.pipe(
            switchMap((watchedResponse: any) => {
                const rateBody = {
                    movieId: movie.data.id,
                    rating: rating,
                    comment: review,
                };

                const rateRequest = this.http.post<any[]>(`/api/movies/rate`, rateBody, {headers});

                return rateRequest.pipe(
                    switchMap((rateResponse: any) => {
                        const castBody = {
                            id: movie.data.id,
                            castId: favoriteCast.id,
                        };

                        const castRequest = this.http.post<any[]>(`/api/movies/favoriteCast`, castBody, {headers});

                        return castRequest.pipe(
                            switchMap((castResponse: any) => {
                                return of([watchedResponse, rateResponse, castResponse]);
                            })
                        );
                    })
                );
            })
        );
    }

}
