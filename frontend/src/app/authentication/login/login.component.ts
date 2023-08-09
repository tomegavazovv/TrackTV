import {Component} from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {ErrorDialogComponent} from "../../core/error-dialog/error-dialog.component";


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {
    email: String = "";
    password: String = "";

    constructor(
        private authService: AuthService,
        private dialogRef: MatDialogRef<LoginComponent>,
        private router: Router,
        private dialog: MatDialog
    ) {
    }

    closeDialog(): void {
        this.dialogRef.close();
    }

    login(): void {
        this.authService.login(this.email, this.password).subscribe({
            next: (response: any) => {
                const token = response.token;
                if (token) {
                    localStorage.setItem('jwtToken', token);
                    this.authService.updateLoginStatus(true);
                    this.closeDialog();
                    this.router.navigate(['/home']).then(() => {
                        console.log('Navigation to home successful!');
                    }).catch((error) => {
                        console.error('Error during navigation:', error);
                    });
                }
            },
            error: error => {
                this.dialog.open(ErrorDialogComponent, {
                    width: '400px',
                    data: { errorMessage: error.error }
                })
            }
        });
    }
}
