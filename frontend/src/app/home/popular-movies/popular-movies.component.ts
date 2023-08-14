import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MovieItem } from 'src/app/interfaces/MovieItem';

@Component({
    selector: 'app-popular-movies',
    templateUrl: './popular-movies.component.html',
    styleUrls: ['./popular-movies.component.css'],
})
export class PopularMoviesComponent implements OnInit {
    popularMovies: MovieItem[] = [];
    loading = false;

    constructor(private http: HttpClient) {}

    ngOnInit(): void {
        this.fetchPopularMovies();
    }

    fetchPopularMovies(): void {
        this.loading = true;
        const apiUrl = '/api/movies/mostPopular';
        this.http.get<any[]>(apiUrl).subscribe({
            next: (data) => {
                this.popularMovies = data;
                this.loading = false;
            },
            error: (error) => {
                console.error('Error fetching popular movies:', error);
                this.loading = false;
            },
        });
    }
}
