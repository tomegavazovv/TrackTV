import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LogMovieComponent} from "./log-movie/log-movie.component";
import {MovieDetailsComponent} from "./movie-details/movie-details.component";
import {RecentlyWatchedComponent} from "./recently-watched/recently-watched.component";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {SharedModule} from "../shared/shared.module";
import {MatMenuModule} from "@angular/material/menu";
import {MatButtonModule} from "@angular/material/button";
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [LogMovieComponent, MovieDetailsComponent, RecentlyWatchedComponent],
    imports: [
        CommonModule,
        MatIconModule,
        MatFormFieldModule,
        SharedModule,
        MatMenuModule,
        MatButtonModule,
        FormsModule
    ]
})
export class MoviesModule { }
