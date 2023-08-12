import {NgModule} from '@angular/core';
import {Routes, RouterModule} from "@angular/router";
import {HomePageComponent} from "./home/home-page/home-page.component";
import {FriendsComponent} from "./friends/friends/friends.component";
import { RegisterComponent} from "./authentication/register/register.component";
import { TvshowComponent } from './pages/tvshow/tvshow.component';
import { MovieComponent } from './pages/movie/movie.component';
import {YourMoviesComponent} from "./movies/your-movies/your-movies.component";
import {YourShowsComponent} from "./tv-shows/your-shows/your-shows.component";
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './home/home-page/home-page.component';
import { FriendsComponent } from './friends/friends/friends.component';
import { RegisterComponent } from './authentication/register/register.component';
import { TvshowComponent } from './details/tvshow-details/tvshow.component';
import { MovieComponent } from './details/movie-details/movie.component';

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
        component: FriendsComponent,
    },
    {
        path: 'movies',
        component: YourMoviesComponent,
    },
    {
        path: 'shows',
        component: YourShowsComponent,
    }
];

@NgModule({
    declarations: [],
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}
