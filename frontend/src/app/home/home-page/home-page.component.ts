import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
    selector: 'app-home-page',
    templateUrl: './home-page.component.html',
    styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
    loggedIn = false;

    constructor(private authService: AuthService) {
    }

    ngOnInit() {
        this.loggedIn = this.authService.isLoggedIn();

        this.authService.getLoginStatus().subscribe((isLoggedIn) => {
            this.loggedIn = isLoggedIn;
        });
    }

}
