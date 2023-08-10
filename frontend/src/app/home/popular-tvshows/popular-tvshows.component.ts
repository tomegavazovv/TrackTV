import {Component, OnInit} from '@angular/core';
import {TvShow} from "../../interfaces/TvShow";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-popular-tvshows',
  templateUrl: './popular-tvshows.component.html',
  styleUrls: ['./popular-tvshows.component.css']
})
export class PopularTvshowsComponent implements OnInit {
    popularTvShows: TvShow[] = [];

    constructor(private http: HttpClient) {}

    ngOnInit(): void {
        this.fetchPopularTvShows();
    }

    fetchPopularTvShows(): void {
        const apiUrl = '/api/tvshows/mostPopular';
        this.http.get<any[]>(apiUrl).subscribe(
            {
                next: (data) => {
                    this.popularTvShows = data;
                },
                error: (error) => {
                    console.error('Error fetching popular movies:', error);
                }
            }
        );
    }
}
