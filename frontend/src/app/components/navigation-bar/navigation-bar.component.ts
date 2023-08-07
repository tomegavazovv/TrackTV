import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {MatDialog} from "@angular/material/dialog";
import {LoginComponent} from "../../pages/login/login.component";
import {Router} from "@angular/router";

@Component({
    selector: 'app-navigation-bar',
    templateUrl: './navigation-bar.component.html',
    styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {
    loggedIn = false;

    constructor(
        private dialog: MatDialog,
        private authService: AuthService,
        private router: Router) {
    }

    ngOnInit() {
        this.loggedIn = this.authService.isLoggedIn()

        this.authService.getLoginStatus().subscribe((isLoggedIn: boolean) => {
            this.loggedIn = isLoggedIn;
        })
    }

    logout() {
        localStorage.removeItem('jwtToken')
        this.authService.updateLoginStatus(false);
        this.router.navigate(['/home']);
    }

    openLoginPopup(): void {
        this.dialog.open(LoginComponent, {
            width: '400px',
            panelClass: 'custom-dialog'
        });
    }

}
