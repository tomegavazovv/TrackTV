import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Response, TvShow } from 'src/app/interfaces/TvShow';

@Injectable({
    providedIn: 'root',
})
export class TvshowServiceService {
    constructor(private http: HttpClient) {}

    getTvShow(tvshowId: Number): Observable<Response> {
        return this.http.get<Response>('http://localhost:8080/api/tvshows/1', {
            headers: {
                // 'X-RapidAPI-Key':
                //     '0437d87ae0msha9573c1586f43f0p14352cjsne62203993c02',
                // 'X-RapidAPI-Host': 'moviesminidatabase.p.rapidapi.com',
                Authorization:
                    'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21lZ2F2YXpvdiIsImV4cCI6MTY5Mjg4MTEzMywiaWF0IjoxNjkwMjg5MTMzLCJ1c2VySWQiOjIsImVtYWlsIjoidG9tZWdhdmF6b3ZAZ21haWwuY29tIn0.VIIhsk9c1xH1MC1bFSCATumlOl2YLggc2REUPGyBvyM',
            },
        });
    }
}
