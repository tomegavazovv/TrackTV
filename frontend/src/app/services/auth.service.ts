import {Injectable} from '@angular/core';
import {Observable, Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private loginStatusSubject: Subject<boolean> = new Subject<boolean>();

    constructor(private http: HttpClient) {
    }

    isLoggedIn(): boolean {
        const jwtToken =localStorage.getItem('jwtToken');
        return !!jwtToken;
    }

    getAuthToken(): string {
        return localStorage.getItem('jwtToken') || '';
    }

    login(email: String, password: String): Observable<any> {
        const loginData = {
            email: email,
            password: password,
        };
        return this.http.post(`/api/login`, loginData);
    }

    getLoginStatus(): Observable<boolean> {
        return this.loginStatusSubject.asObservable()
    }

    updateLoginStatus(isLoggedIn: boolean): void {
        this.loginStatusSubject.next(isLoggedIn);
    }

}
