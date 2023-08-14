import { Component, OnInit } from '@angular/core';
import { MovieTvService } from '../../services/movie-tv.service';
import { WatchedMovie } from '../../interfaces/WatchedMovie';
import { MatDialog } from '@angular/material/dialog';
import { SearchUsersComponent } from '../../friends/search-users/search-users.component';

@Component({
    selector: 'app-your-movies',
    templateUrl: './your-movies.component.html',
    styleUrls: ['./your-movies.component.css'],
})
export class YourMoviesComponent implements OnInit {
    yourMovies: WatchedMovie[] = [];
    loading = false;

    constructor(
        private movieTvService: MovieTvService,
        private dialog: MatDialog
    ) {}

    fetchYourMovies(): void {
        this.loading = true;
        this.movieTvService.getWatchedMovies().subscribe({
            next: (data) => {
                this.yourMovies = data;
                this.loading = false;
            },
            error: (err) => {
                console.log(err);
                this.loading = false;
            },
        });
    }

    ngOnInit(): void {
        this.fetchYourMovies();
    }

    suggestToFriend(movie: WatchedMovie): void {
        movie.type = 'movie';
        this.dialog.open(SearchUsersComponent, {
            width: '500px',
            panelClass: 'custom-dialog',
            data: {
                isFriendRequest: false,
                isSuggestion: true,
                suggestion: movie,
            },
        });
    }
}
