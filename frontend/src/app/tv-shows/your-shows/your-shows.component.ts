import {Component, OnInit} from '@angular/core';
import {WatchedShow} from "../../interfaces/WatchedShow";
import {MovieTvService} from "../../services/movie-tv.service";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {SearchUsersComponent} from "../../friends/search-users/search-users.component";

@Component({
  selector: 'app-your-shows',
  templateUrl: './your-shows.component.html',
  styleUrls: ['./your-shows.component.css']
})
export class YourShowsComponent implements OnInit{

    yourShows: WatchedShow[] = [];

    constructor(private movieTvService: MovieTvService, private dialog: MatDialog) {
    }

    fetchYourShows(): void {
        this.movieTvService.getWatchedShows().subscribe({
            next: (data) => {
                this.yourShows = data;
            },
            error: (err) => {
                console.log(err)
            }
        })
    }

    ngOnInit(): void {
        this.fetchYourShows()
    }

    suggestToFriend(show: WatchedShow): void {
        const dialogRef: MatDialogRef<SearchUsersComponent> = this.dialog.open(SearchUsersComponent, {
            width: '500px',
            panelClass: 'custom-dialog'
        });
        dialogRef.componentInstance.isFriendRequest = false;
        dialogRef.componentInstance.isSuggestion = true;
        dialogRef.componentInstance.suggestion = show;
    }


}
