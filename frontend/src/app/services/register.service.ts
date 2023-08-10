import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class RegisterService {
    private apiUrl = '/api/register';

    constructor(private http: HttpClient) { }

    signup(registerData: any) {
        return this.http.post(this.apiUrl, registerData);
    }
}
