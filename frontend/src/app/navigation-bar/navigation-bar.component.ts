import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";
import {MatDialog} from "@angular/material/dialog";
import {LoginComponent} from "../login/login.component";

@Component({
    selector: 'app-navigation-bar',
    templateUrl: './navigation-bar.component.html',
    styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {
    loggedIn = false;

    constructor(
        private dialog: MatDialog,
        private authService: AuthService) {
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

    }

    openLoginPopup(): void {
        this.dialog.open(LoginComponent, {
            width: '400px',
            panelClass: 'custom-dialog'
        });
    }

}
