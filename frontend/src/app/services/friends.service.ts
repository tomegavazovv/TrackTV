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

    private getAuthorizationHeader(): HttpHeaders {
        return new HttpHeaders({
            'Authorization': `${localStorage.getItem('jwtToken')}`
        });
    }

    getFriends(): Observable<User[]> {
        return this.http.get<User[]>('/api/friends', {headers: this.getAuthorizationHeader()});
    }

    getRequests(): Observable<FriendRequest[]> {
        return this.http.get<FriendRequest[]>(`/api/friendRequests`, {headers: this.getAuthorizationHeader()});
    }

    removeFriend(friendId: number): Observable<any> {
        return this.http.delete<any>(`/api/removeFriend/${friendId}`, {headers: this.getAuthorizationHeader()});
    }

    searchUsers(searchTerm: string): Observable<User[]> {
        return this.http.get<User[]>(`/api/searchUsers?username=${searchTerm}`, {headers: this.getAuthorizationHeader()});
    }

    sendFriendRequest(friendId: number): Observable<any> {
        return this.http.post<any>(`/api/addFriend/${friendId}`, {}, {headers: this.getAuthorizationHeader()});
    }

    acceptRequest(requestId: number): Observable<any> {
        return this.http.post<any>(`/api/acceptRequest/${requestId}`, {}, {headers: this.getAuthorizationHeader()});
    }

    declineRequest(requestId: number): Observable<any> {
        return this.http.post<any>(`/api/declineRequest/${requestId}`, {}, {headers: this.getAuthorizationHeader()}).pipe(
            catchError((error: HttpErrorResponse) => {
                return throwError(error.error.error);
            }));
    }


}
