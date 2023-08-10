import {NgModule} from '@angular/core';
import {Routes, RouterModule} from "@angular/router";
import {HomePageComponent} from "./home/home-page/home-page.component";
import {RegisterComponent} from "./authentication/register/register.component";
import {TvShowDetailsComponent} from "./tv-shows/tvshow-details/tv-show-details.component";
import {FriendsComponent} from "./friends/friends/friends.component";
import {MovieDetailsComponent} from "./movies/movie-details/movie-details.component";
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { RegisterComponent } from './register/register.component';
import { TvshowComponent } from './pages/tvshow/tvshow.component';
import { MovieComponent } from './pages/movie/movie.component';

const routes: Routes = [
    {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full',
    },
    {
        path: 'home',
        component: HomePageComponent,
    },
    {
        path: 'register',
        component: RegisterComponent,
    },
    {
        path: 'tvshows/:id',
        component: TvshowComponent,
    },
    {
        path: 'movies/:id',
        component: MovieComponent,
    },
    {
        path: 'friends',
        component: FriendsComponent
    }
];

@NgModule({
    declarations: [],
    imports: [RouterModule.forChild(routes), RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
