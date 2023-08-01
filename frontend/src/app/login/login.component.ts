import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {
    email: string | undefined;
    password: string | undefined;

    constructor(
        private dialogRef: MatDialogRef<LoginComponent>,
        private http: HttpClient
    ) {}


    closeDialog(): void {
        this.dialogRef.close();
    }

    login(): void {
        const requestBody = {
            email: this.email,
            password: this.password
        };

        this.http.post('http://localhost:8080/api/login', requestBody).subscribe(
            (response) => {
                // Handle the response from the server after successful login.
                console.log('Login successful!', response);
            },
            (error) => {
                // Handle the error if login fails.
                console.error('Login failed!', error);
            }
        );
    }
}
