import {Component, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";
import {FriendsService} from "../services/friends.service";
import {debounceTime, distinctUntilChanged, switchMap} from "rxjs";
import {User} from "../interfaces/user";
import {MatDialog} from "@angular/material/dialog";
import {ErrorDialogComponent} from "../error-dialog/error-dialog.component";

@Component({
  selector: 'app-search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.css']
})
export class SearchUsersComponent implements OnInit{
    searchForm: FormControl = new FormControl('');
    users: User[] = [];

    constructor(private friendsService: FriendsService, private dialog: MatDialog) {
    }

    ngOnInit(): void {
        this.searchForm.valueChanges.pipe(
            distinctUntilChanged(),
            debounceTime(400),
            switchMap(word => this.friendsService.searchUsers(word)),
        ).subscribe({
            next: (data: User[]) => {
                this.users = data;
            }, error: error => {
                console.error('Error fetching users:', error);
            }
        })
    }

    sendFriendRequest(user: User) {
        this.friendsService.sendFriendRequest(user.id).subscribe({
            error: error => {
                this.dialog.open(ErrorDialogComponent, {
                    width: '400px',
                    data: { errorMessage: error.error.error }
                })
            }
        })
    }
}
