import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { SearchBarComponent } from './pages/home-page/search-bar/search-bar.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RecentlyWatchedComponent } from './recently-watched/recently-watched.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { AppRoutingModule } from './app-routing.module';
import { CommonModule } from '@angular/common';
import { PopularMoviesComponent } from './popular-movies/popular-movies.component';
import { PopularTvshowsComponent } from './popular-tvshows/popular-tvshows.component';
import { LoginComponent } from './login/login.component';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { RegisterComponent } from './register/register.component';
import { TvshowComponent } from './pages/tvshow/tvshow.component';
import { EpisodeListComponent } from './pages/tvshow/episode-list/episode-list.component';
import { CastListComponent } from './pages/tvshow/cast-list/cast-list.component';
import { CommentsComponent } from './pages/tvshow/comments/comments.component';
import { MovieComponent } from './pages/movie/movie.component';

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
        TvshowComponent,
        EpisodeListComponent,
        CastListComponent,
        CommentsComponent,
        MovieComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        ReactiveFormsModule,
        AppRoutingModule,
        CommonModule,
        FormsModule,
        MatButtonModule,
        MatDialogModule,
        MatInputModule,
        BrowserAnimationsModule,
        MatCardModule,
    ],

    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {}
