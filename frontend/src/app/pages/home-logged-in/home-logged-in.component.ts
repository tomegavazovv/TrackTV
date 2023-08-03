import {Component, OnInit} from '@angular/core';
import {Movie} from "../../interfaces/movie";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TvShow} from "../../interfaces/tvshow";

@Component({
    selector: 'app-home-logged-in',
    templateUrl: './home-logged-in.component.html',
    styleUrls: ['./home-logged-in.component.css']
})
export class HomeLoggedInComponent implements OnInit {
    searchTerm: string = '';
    searchResults: Movie[] = [];
    watchedShows: TvShow[] = [];

    constructor(private http: HttpClient) {
    }

    ngOnInit(): void {
        this.fetchWatchedShows()
    }

    searchMovies() {
        const headers = new HttpHeaders({
            'Authorization': `${localStorage.getItem('jwtToken')}`
        });

        this.http.get<Movie[]>(`/api/movies/search?title=${this.searchTerm}`, {headers}).subscribe({
                next: data => {
                    this.searchResults = data;
                },
                error: error => {
                    console.error('Error fetching movies:', error);
                }
            }
        );
    }

    showMovieDetails(movie: Movie) {
    }

    fetchWatchedShows(): void {
        const headers = new HttpHeaders({
            'Authorization': `${localStorage.getItem('jwtToken')}`
        });
        this.http.get<any[]>('/api/tvshows/watched', {headers}).subscribe({
                next: data => {
                    this.watchedShows = data.map((item) => item.show);
                },
                error: error => {
                    console.error('Error fetching popular movies:', error);
                }
            }
        );
    }
}

