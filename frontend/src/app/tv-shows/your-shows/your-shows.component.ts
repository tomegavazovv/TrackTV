import { Component, OnInit } from '@angular/core';
import { WatchedShow } from '../../interfaces/WatchedShow';
import { MovieTvService } from '../../services/movie-tv.service';
import { MatDialog } from '@angular/material/dialog';
import { SearchUsersComponent } from '../../friends/search-users/search-users.component';
import { FriendListComponent } from 'src/app/friends/friend-list/friend-list.component';

@Component({
    selector: 'app-your-shows',
    templateUrl: './your-shows.component.html',
    styleUrls: ['./your-shows.component.css'],
})
export class YourShowsComponent implements OnInit {
    yourShows: WatchedShow[] = [];
    loading = false;

    constructor(
        private movieTvService: MovieTvService,
        private dialog: MatDialog
    ) {}

    fetchYourShows(): void {
        this.loading = true;
        this.movieTvService.getWatchedShows().subscribe({
            next: (data) => {
                this.yourShows = data;
                this.loading = false;
            },
            error: (err) => {
                console.log(err);
                this.loading = false;
            },
        });
    }

    ngOnInit(): void {
        this.fetchYourShows();
    }

    suggestToFriend(show: WatchedShow): void {
        show.type = 'show';
        this.dialog.open(FriendListComponent, {
            width: '500px',
            panelClass: 'custom-dialog',
            data: {
                suggestion: show,
            },
        });
    }
}
