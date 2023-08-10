import {Component, Inject, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {LoginComponent} from "../../authentication/login/login.component";
import {Movie} from "../../interfaces/movie";
import {Cast} from "../../interfaces/cast";
import {CastService} from "../../services/cast.service";
import {MovieTvService} from "../../services/movie-tv.service";

@Component({
    selector: 'app-log-movie',
    templateUrl: './log-movie.component.html',
    styleUrls: ['./log-movie.component.css']
})
export class LogMovieComponent implements OnInit {
    movie: Movie;
    review: String = "";
    rating: Number = 0;
    cast: Cast[] = [];
    favoriteCast: Cast = {} as Cast

    constructor(
        private authService: AuthService,
        private dialogRef: MatDialogRef<LoginComponent>,
        private castService: CastService,
        private movieTvService: MovieTvService,
        @Inject(MAT_DIALOG_DATA) public data: Movie
    ) {
        this.movie = data;
    }

    closeDialog(): void {
        this.dialogRef.close();
    }

    openFavoriteCastDialog(): void {
        this.dialogRef.close();
    }

    logMovie(): void {
        this.movieTvService.logMovie(this.movie, this.rating, this.review, this.favoriteCast).subscribe({
            next: () => {},
            error: (error) => {
                console.error('Error logging the movie:', error);
            }
        })
        this.closeDialog();
    }

    ngOnInit(): void {
        this.getMovieCast();
    }

    getMovieCast(): void {
        this.castService.getMovieCast(this.movie.data.id).subscribe({
                next: (data: Cast[]) => {
                    this.cast = data;
                    console.log(data)
                },
                error: error => {
                    console.error('Error fetching cast:', error);
                }
            }
        );
    }

    onRatingChanged(rating: number): void {
        this.rating = rating;
        console.log(this.rating);
    }
}
