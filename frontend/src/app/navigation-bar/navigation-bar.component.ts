import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth.service";

@Component({
    selector: 'app-navigation-bar',
    templateUrl: './navigation-bar.component.html',
    styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {
    loggedIn = false;

    constructor(private authService: AuthService) {
    }

    ngOnInit() {
        this.loggedIn = this.authService.isLoggedIn()
    }

    logout() {
        localStorage.removeItem('token')
        this.loggedIn = false;

    }

}
