import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Movie} from "../../interfaces/movie";

@Component({
    selector: 'app-popular-movies',
    templateUrl: './popular-movies.component.html',
    styleUrls: ['./popular-movies.component.css']
})
export class PopularMoviesComponent implements OnInit {
    popularMovies: Movie[] = [];

    constructor(private http: HttpClient) {
    }

    ngOnInit(): void {
        this.fetchPopularMovies();
    }

    fetchPopularMovies(): void {
        const apiUrl = '/api/movies/mostPopular';
        this.http.get<any[]>(apiUrl).subscribe({
                next: data => {
                    this.popularMovies = data;
                },
                error: error => {
                    console.error('Error fetching popular movies:', error);
                }
            }
        );


    }
}
