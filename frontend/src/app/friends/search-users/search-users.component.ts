import { Component, Inject, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { FriendsService } from '../../services/friends.service';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { User } from '../../interfaces/user';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../core/error-dialog/error-dialog.component';
import { WatchedMovie } from '../../interfaces/WatchedMovie';
import { WatchedShow } from '../../interfaces/WatchedShow';

@Component({
    selector: 'app-search-users',
    templateUrl: './search-users.component.html',
    styleUrls: ['./search-users.component.css'],
})
export class SearchUsersComponent implements OnInit {
    searchForm: FormControl = new FormControl('');
    users: User[] = [];
    suggestion: WatchedMovie | WatchedShow = {} as WatchedMovie | WatchedShow;
    isFriendRequest: boolean = false;
    isSuggestion: boolean = false;

    constructor(
        @Inject(MAT_DIALOG_DATA) public data: any,
        private friendsService: FriendsService,
        private dialog: MatDialog
    ) {}

    ngOnInit(): void {
        this.isFriendRequest = this.data.isFriendRequest;
        this.isSuggestion = this.data.isSuggestion;
        this.suggestion = this.data.suggestion;

        this.searchForm.valueChanges
            .pipe(
                distinctUntilChanged(),
                debounceTime(400),
                switchMap((word) =>
                    this.friendsService.searchUsers(
                        word,
                        this.isFriendRequest,
                        this.isSuggestion
                    )
                )
            )
            .subscribe({
                next: (data: User[]) => {
                    this.users = data;
                },
                error: (error) => {
                    console.error('Error fetching users:', error);
                },
            });
    }

    sendFriendRequest(user: User) {
        this.friendsService.sendFriendRequest(user.id).subscribe({
            error: (error) => {
                this.dialog.open(ErrorDialogComponent, {
                    width: '400px',
                    data: { errorMessage: error.error.error },
                });
            },
        });
    }

    suggest(suggestion: WatchedMovie | WatchedShow, user: User): void {
        if (suggestion.type === 'movie') {
            this.friendsService
                .suggestMovie(suggestion.data.id, user.id)
                .subscribe({
                    error: (error) => {
                        this.dialog.open(ErrorDialogComponent, {
                            width: '400px',
                            data: { errorMessage: error },
                        });
                    },
                });
        } else if (suggestion.type === 'show') {
            this.friendsService
                .suggestShow(suggestion.data.id.valueOf(), user.id)
                .subscribe({
                    error: (error) => {
                        this.dialog.open(ErrorDialogComponent, {
                            width: '400px',
                            data: { errorMessage: error.error.error },
                        });
                    },
                });
        }
    }
}
