import {Component, OnInit} from '@angular/core';
import {MovieTvService} from "../../services/movie-tv.service";
import {WatchedMovie} from "../../interfaces/WatchedMovie";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {SearchUsersComponent} from "../../friends/search-users/search-users.component";

@Component({
  selector: 'app-your-movies',
  templateUrl: './your-movies.component.html',
  styleUrls: ['./your-movies.component.css']
})
export class YourMoviesComponent implements OnInit{

    yourMovies: WatchedMovie[] = [];

    constructor(private movieTvService: MovieTvService, private dialog: MatDialog) {
    }

    fetchYourMovies(): void {
        this.movieTvService.getWatchedMovies().subscribe({
            next: (data) => {
                this.yourMovies = data;
            },
            error: (err) => {
                console.log(err)
            }
        })
    }

    ngOnInit(): void {
        this.fetchYourMovies()
    }

    suggestToFriend(movie: WatchedMovie): void {
        const dialogRef: MatDialogRef<SearchUsersComponent> = this.dialog.open(SearchUsersComponent, {
            width: '500px',
            panelClass: 'custom-dialog'
        });
        dialogRef.componentInstance.isFriendRequest = false;
        dialogRef.componentInstance.isSuggestion = true;
        dialogRef.componentInstance.suggestion = movie;
    }
}
