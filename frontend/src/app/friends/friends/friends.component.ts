import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {User} from "../../interfaces/user";
import {FriendsService} from "../../services/friends.service";
import {MatDialog} from "@angular/material/dialog";
import {SearchUsersComponent} from "../search-users/search-users.component";
import {FriendRequestsComponent} from "../friend-requests/friend-requests.component";
import {tap} from "rxjs";

@Component({
    selector: 'app-friends',
    templateUrl: './friends.component.html',
    styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {
    friends: User[] = [];

    constructor(
        private cdr: ChangeDetectorRef,
        private friendsService: FriendsService,
        private dialog: MatDialog) {
    }

    ngOnInit(): void {
        this.getFriends()
    }

    removeFriend(friend: User): void {
        this.friendsService.removeFriend(friend.id).pipe(
            tap( () => {
                this.getFriends();
            })
        ).subscribe({
            error: (err) => {
                console.log(err.error);
            }
        });
    }

    showSuggestions(): void {

    }

    getFriends(): void {
        this.friendsService.getFriends().subscribe({
            next: (response) => {
                this.friends = response;
            },
            error: (err) => {
                if (err.status === 404) {
                    this.friends = [];
                } else {
                    console.log(err.error);
                }
            }
        });
    }


    searchUsers(): void {
        this.dialog.open(SearchUsersComponent, {
            width: '500px',
            panelClass: 'custom-dialog',
        })
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

}
