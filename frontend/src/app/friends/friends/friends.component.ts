import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { User } from '../../interfaces/User';
import { FriendsService } from '../../services/friends.service';
import { MatDialog } from '@angular/material/dialog';
import { SearchUsersComponent } from '../search-users/search-users.component';
import { FriendRequestsComponent } from '../friend-requests/friend-requests.component';
import { tap } from 'rxjs';
import { SuggestionsComponent } from '../suggestions/suggestions.component';

@Component({
    selector: 'app-friends',
    templateUrl: './friends.component.html',
    styleUrls: ['./friends.component.css'],
})
export class FriendsComponent implements OnInit {
    friends: User[] = [];
    loading = false;

    constructor(
        private friendsService: FriendsService,
        private dialog: MatDialog
    ) {}

    ngOnInit(): void {
        this.getFriends();
    }

    removeFriend(friend: User): void {
        this.friendsService
            .removeFriend(friend.id)
            .pipe(
                tap(() => {
                    this.getFriends();
                })
            )
            .subscribe({
                error: (err) => {
                    console.log(err.error);
                },
            });
    }

    getFriends(): void {
        this.loading = true;
        this.friendsService.getFriends().subscribe({
            next: (response) => {
                this.friends = response;
                this.loading = false;
            },
            error: (err) => {
                this.loading = false;
                if (err.status === 404) {
                    this.friends = [];
                } else {
                    console.log(err.error);
                }
            },
        });
    }

    searchUsers(): void {
        this.dialog.open(SearchUsersComponent, {
            width: '500px',
            panelClass: 'custom-dialog',
            data: {
                isFriendRequest: true,
                isSuggestion: false,
                suggestion: undefined,
            },
        });
    }

    viewRequests(): void {
        const dialogRef = this.dialog.open(FriendRequestsComponent, {
            width: '500px',
            panelClass: 'custom-dialog',
        });

        dialogRef.componentInstance.requestAccepted.subscribe(() => {
            this.getFriends();
        });
    }

    viewSuggestions() {
        this.dialog.open(SuggestionsComponent, {
            width: '800px',
            panelClass: 'custom-dialog',
        });
    }
}
