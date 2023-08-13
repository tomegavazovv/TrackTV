import { Component, OnInit } from '@angular/core';
import { Movie } from '../../interfaces/movie';
import { TvShow } from 'src/app/interfaces/TvShow';
import { MovieTvService } from '../../services/movie-tv.service';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { LogMovieComponent } from '../../movies/log-movie/log-movie.component';
import { MovieItem } from 'src/app/interfaces/MovieItem';
import {WatchedShow} from "../../interfaces/WatchedShow";
import {PopularTvShow} from "../../interfaces/PopularTvShow";

@Component({
    selector: 'app-home-logged-in',
    templateUrl: './home-logged-in.component.html',
    styleUrls: ['./home-logged-in.component.css'],
})
export class HomeLoggedInComponent implements OnInit {
    searchTerm: string = '';
    movieSearchResults: MovieItem[] = [];
    tvshowSearchResults: PopularTvShow[] = [];
    watchedShows: WatchedShow[] = [];
    movieSearchForm: FormControl = new FormControl('');
    tvshowSearchForm: FormControl = new FormControl('');

    constructor(
        private movieTvService: MovieTvService,
        private dialog: MatDialog
    ) {}

    ngOnInit(): void {
        this.fetchWatchedShows();

        this.movieSearchForm.valueChanges
            .pipe(
                distinctUntilChanged(),
                debounceTime(400),
                switchMap((word) => this.movieTvService.searchMovies(word))
            )
            .subscribe({
                next: (data: MovieItem[]) => {
                    this.movieSearchResults = data;
                },
                error: (error) => {
                    console.error('Error fetching movies:', error);
                },
            });
        this.tvshowSearchForm.valueChanges
            .pipe(
                distinctUntilChanged(),
                debounceTime(400),
                switchMap((word) => this.movieTvService.searchTvShows(word))
            )
            .subscribe({
                next: (data: PopularTvShow[]) => {
                    this.tvshowSearchResults = data;
                },
                error: (error) => {
                    console.error('Error fetching tv shows:', error);
                },
            });
    }

    showMovieDetails(movie: Movie): void {
        this.dialog.open(LogMovieComponent, {
            width: '400px',
            panelClass: 'custom-dialog',
            data: movie,
        });
    }

    fetchWatchedShows(): void {
        this.movieTvService.getWatchedShows().subscribe({
            next: (data: WatchedShow[]) => {
                this.watchedShows = data;
            },
            error: (error) => {
                console.error('Error fetching watched shows:', error);
            },
        });
    }
}
