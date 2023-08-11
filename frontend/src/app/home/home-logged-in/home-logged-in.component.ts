import { Component, OnInit } from '@angular/core';
import { Movie } from '../../interfaces/movie';
import { TvShow } from 'src/app/interfaces/TvShow';
import { MovieTvService } from '../../services/movie-tv.service';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { LogMovieComponent } from '../../movies/log-movie/log-movie.component';
import { MovieItem } from 'src/app/interfaces/MovieItem';

@Component({
    selector: 'app-home-logged-in',
    templateUrl: './home-logged-in.component.html',
    styleUrls: ['./home-logged-in.component.css'],
})
export class HomeLoggedInComponent implements OnInit {
    searchTerm: string = '';
    searchResults: MovieItem[] = [];
    watchedShows: TvShow[] = [];
    searchForm: FormControl = new FormControl('');

    constructor(
        private movieTvService: MovieTvService,
        private dialog: MatDialog
    ) {}

    ngOnInit(): void {
        this.fetchWatchedShows();

        this.searchForm.valueChanges
            .pipe(
                distinctUntilChanged(),
                debounceTime(400),
                switchMap((word) => this.movieTvService.searchMovies(word))
            )
            .subscribe({
                next: (data: MovieItem[]) => {
                    this.searchResults = data;
                },
                error: (error) => {
                    console.error('Error fetching movies:', error);
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
            next: (data: TvShow[]) => {
                this.watchedShows = data;
            },
            error: (error) => {
                console.error('Error fetching watched shows:', error);
            },
        });
    }
}
