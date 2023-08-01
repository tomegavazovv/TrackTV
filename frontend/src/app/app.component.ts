import {Component} from '@angular/core';
import {LoginComponent} from "./login/login.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
})
export class AppComponent {

    constructor() {
    }

    title = 'TrackTV';

}
