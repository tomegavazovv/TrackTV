import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {User} from "../interfaces/user";
import {FriendRequest} from "../interfaces/friendRequest";

@Injectable({
    providedIn: 'root'
})
export class FriendsService {

    constructor(private http: HttpClient) {
    }


    getFriends(): Observable<User[]> {
        return this.http.get<User[]>('/api/friends');
    }

    getRequests(): Observable<FriendRequest[]> {
        return this.http.get<FriendRequest[]>(`/api/friendRequests`);
    }

    removeFriend(friendId: number): Observable<any> {
        return this.http.delete<any>(`/api/removeFriend/${friendId}`);
    }

    searchUsers(searchTerm: string): Observable<User[]> {
        return this.http.get<User[]>(`/api/searchUsersClean?username=${searchTerm}`);
    }

    sendFriendRequest(friendId: number): Observable<any> {
        return this.http.post<any>(`/api/addFriend/${friendId}`, {});
    }

    acceptRequest(requestId: number): Observable<any> {
        return this.http.post<any>(`/api/acceptRequest/${requestId}`, {});
    }

    declineRequest(requestId: number): Observable<any> {
        return this.http.post<any>(`/api/declineRequest/${requestId}`, {},).pipe(
            catchError((error: HttpErrorResponse) => {
                return throwError(error.error.error);
            }));
    }


}
