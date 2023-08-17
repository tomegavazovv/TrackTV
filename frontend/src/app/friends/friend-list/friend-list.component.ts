import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Suggestion } from 'src/app/interfaces/Suggestion';
import { User } from 'src/app/interfaces/User';
import { WatchedMovie } from 'src/app/interfaces/WatchedMovie';
import { WatchedShow } from 'src/app/interfaces/WatchedShow';
import { FriendsService } from 'src/app/services/friends.service';

@Component({
    selector: 'app-friend-list',
    templateUrl: './friend-list.component.html',
    styleUrls: ['./friend-list.component.css'],
})
export class FriendListComponent implements OnInit {
    friends: User[] = [];
    suggestion: WatchedMovie | WatchedShow = {} as WatchedMovie | WatchedShow;
    mySuggestions: Suggestion[] = [];
    loading = false;

    constructor(
        private friendsService: FriendsService,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) {}

    ngOnInit() {
        this.loading = true;
        this.friendsService.getFriends().subscribe((friends) => {
            this.friends = friends;
        });
        this.suggestion = this.data.suggestion as WatchedMovie | WatchedShow;
        this.loadSuggestions();
    }

    private loadSuggestions() {
        if (this.suggestion.type == 'movie') {
            this.friendsService
                .getMySuggestionsForMovie(this.suggestion.data.id)
                .subscribe((suggestions) => {
                    this.mySuggestions = suggestions;
                    this.loading = false;
                });
        } else {
            this.friendsService
                .getMySuggestionsForShow(+this.suggestion.data.id)
                .subscribe((suggestions) => {
                    this.mySuggestions = suggestions;
                    this.loading = false;
                });
        }
    }

    onClickSuggest(friendId: number) {
        if (this.suggestion) {
            if (this.suggestion.type == 'movie') {
                this.friendsService
                    .suggestMovie(this.suggestion.data.id.valueOf(), friendId)
                    .subscribe((_) => this.handleSuggestResponse(friendId));
            } else {
                this.friendsService
                    .suggestShow(this.suggestion.data.id.valueOf(), friendId)
                    .subscribe((_) => this.handleSuggestResponse(friendId));
            }
        }
    }

    private handleSuggestResponse(friendId: number) {
        this.mySuggestions = [
            ...this.mySuggestions,
            {
                userId: friendId,
                movieId: this.suggestion!.data.id.valueOf(),
            },
        ];
    }

    hasSuggested(friendId: number): Boolean {
        return this.mySuggestions.findIndex((s) => s.userId == friendId) != -1;
    }
}
