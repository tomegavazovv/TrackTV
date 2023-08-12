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

    searchUsers(searchTerm: string, isFriendRequest: boolean, isMovieSuggestion: boolean): Observable<User[]> {
        if (isFriendRequest && !isMovieSuggestion) {
            return this.http.get<User[]>(`/api/searchUsersClean?username=${searchTerm}`);
        } else if(!isFriendRequest && isMovieSuggestion) {
            return this.http.get<User[]>('/api/friends')
        } else {
            return new Observable<User[]>()
        }
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

    suggestMovie(movieId: number, userId: number): Observable<any> {
        return this.http.post<any>(`/api/suggestMovie/${movieId}/${userId}`, {},).pipe(
            catchError((error: HttpErrorResponse) => {
                return throwError(error.error.error);
            }));
    }

    suggestShow(showId: number, userId: number): Observable<any> {
        return this.http.post<any>(`/api/suggestShow/${showId}/${userId}`, {},).pipe(
            catchError((error: HttpErrorResponse) => {
                return throwError(error.error.error);
            }));
    }

}
