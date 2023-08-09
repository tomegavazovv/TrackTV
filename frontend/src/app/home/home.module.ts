import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HomeLoggedInComponent} from "./home-logged-in/home-logged-in.component";
import {HomeLoggedOutComponent} from "./home-logged-out/home-logged-out.component";
import {HomePageComponent} from "./home-page/home-page.component";
import {MatListModule} from "@angular/material/list";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {AppRoutingModule} from "../app-routing.module";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {PopularMoviesComponent} from "./popular-movies/popular-movies.component";
import {PopularTvshowsComponent} from "./popular-tvshows/popular-tvshows.component";


@NgModule({
  declarations: [HomeLoggedInComponent, HomeLoggedOutComponent, HomePageComponent, PopularMoviesComponent, PopularTvshowsComponent],
    imports: [
        CommonModule,
        MatListModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        AppRoutingModule,
        MatCardModule,
        MatButtonModule
    ]
})
export class HomeModule { }
