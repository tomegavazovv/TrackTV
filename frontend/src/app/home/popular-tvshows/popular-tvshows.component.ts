import { Component, OnInit } from '@angular/core';
import { TvShow } from '../../interfaces/TvShow';
import { HttpClient } from '@angular/common/http';
import { PopularTvShow } from 'src/app/interfaces/PopularTvShow';

@Component({
    selector: 'app-popular-tvshows',
    templateUrl: './popular-tvshows.component.html',
    styleUrls: ['./popular-tvshows.component.css'],
})
export class PopularTvshowsComponent implements OnInit {
    popularTvShows: PopularTvShow[] = [];
    loading = false;

    constructor(private http: HttpClient) {}

    ngOnInit(): void {
        this.fetchPopularTvShows();
    }

    fetchPopularTvShows(): void {
        this.loading = true;
        const apiUrl = '/api/tvshows/mostPopular';
        this.http.get<any[]>(apiUrl).subscribe({
            next: (data) => {
                this.popularTvShows = data;
                this.loading = false;
            },
            error: (error) => {
                console.error('Error fetching popular movies:', error);
                this.loading = false;
            },
        });
    }
}
