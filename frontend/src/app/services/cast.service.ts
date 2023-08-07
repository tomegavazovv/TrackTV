import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Cast} from "../interfaces/cast";

@Injectable({
    providedIn: 'root'
})
export class CastService {

    constructor(private http: HttpClient) {
    }

    private getAuthorizationHeader(): HttpHeaders {
        return new HttpHeaders({
            'Authorization': `${localStorage.getItem('jwtToken')}`
        });
    }

    getMovieCast(movieId: number): Observable<Cast[]> {
        return this.http.get<Cast[]>(`/api/movies/topCast/${movieId}`, {headers: this.getAuthorizationHeader()});
    }
}
