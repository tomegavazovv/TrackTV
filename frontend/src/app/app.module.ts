import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {SearchBarComponent} from './pages/search-bar/search-bar.component';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RecentlyWatchedComponent} from './pages/recently-watched/recently-watched.component';
import {NavigationBarComponent} from "./components/navigation-bar/navigation-bar.component";
import {AppRoutingModule} from "./app-routing.module";
import {CommonModule} from "@angular/common";
import {PopularMoviesComponent} from './pages/popular-movies/popular-movies.component';
import {PopularTvshowsComponent} from './pages/popular-tvshows/popular-tvshows.component';
import {LoginComponent} from "./pages/login/login.component";
import {MatButtonModule} from "@angular/material/button";
import {MatDialogModule} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatCardModule} from "@angular/material/card";
import {RegisterComponent} from './pages/register/register.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {FooterComponent} from './components/footer/footer.component';
import {MatIconModule} from "@angular/material/icon";
import {HomeLoggedInComponent} from './pages/home-logged-in/home-logged-in.component';
import {HomeLoggedOutComponent} from './pages/home-logged-out/home-logged-out.component';
import {MatListModule} from "@angular/material/list";
import {LogMovieComponent} from './log-movie/log-movie.component';
import {MatMenuModule} from "@angular/material/menu";
import {StarRatingComponent} from "./pages/star-rating/star-rating.component";
import { MatTooltipModule } from '@angular/material/tooltip';

@NgModule({
    declarations: [
        AppComponent,
        SearchBarComponent,
        HomePageComponent,
        RecentlyWatchedComponent,
        NavigationBarComponent,
        PopularMoviesComponent,
        PopularTvshowsComponent,
        LoginComponent,
        RegisterComponent,
        FooterComponent,
        HomeLoggedInComponent,
        HomeLoggedOutComponent,
        LogMovieComponent,
        StarRatingComponent,
    ],
    imports: [MatTooltipModule, BrowserModule, HttpClientModule, ReactiveFormsModule, AppRoutingModule, CommonModule, FormsModule, MatButtonModule, MatDialogModule, MatInputModule, BrowserAnimationsModule, MatCardModule, MatToolbarModule, MatIconModule, MatListModule, MatMenuModule],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {
}
