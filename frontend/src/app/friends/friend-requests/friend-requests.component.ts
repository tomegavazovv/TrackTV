import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { User } from '../../interfaces/User';
import { FriendsService } from '../../services/friends.service';
import { map, tap } from 'rxjs';

@Component({
    selector: 'app-friend-requests',
    templateUrl: './friend-requests.component.html',
    styleUrls: ['./friend-requests.component.css'],
})
export class FriendRequestsComponent implements OnInit {
    @Output() requestAccepted = new EventEmitter<void>();
    requests: User[] = [];

    constructor(private friendsService: FriendsService) {}

    ngOnInit(): void {
        this.getRequests();
    }

    getRequests(): void {
        this.friendsService
            .getRequests()
            .pipe(
                map((response) => response.map((request) => request.senderId))
            )
            .subscribe({
                next: (response) => {
                    this.requests = response;
                },
                error: (err) => {},
            });
    }

    acceptRequest(request: User): void {
        this.friendsService
            .acceptRequest(request.id)
            .pipe(
                tap(() => {
                    this.requestAccepted.emit();
                    this.getRequests();
                })
            )
            .subscribe({
                error: (err) => {
                    console.log(err);
                },
            });
    }

    declineRequest(request: User): void {
        this.friendsService
            .declineRequest(request.id)
            .pipe(
                tap(() => {
                    this.getRequests();
                })
            )
            .subscribe({
                error: (err) => {
                    console.log(err);
                },
            });
    }
}
