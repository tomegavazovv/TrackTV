import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './auth.interceptor';
import { CoreModule } from './core/core.module';
import { AuthenticationModule } from './authentication/authentication.module';
import { SharedModule } from './shared/shared.module';
import { HomeModule } from './home/home.module';
import { FriendsModule } from './friends/friends.module';
import { TvShowsModule } from './tv-shows/tv-shows.module';
import { MoviesModule } from './movies/movies.module';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { MovieComponent } from './details/movie-details/movie.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { FavoriteCastBarComponent } from './details/favorite-cast-bar/favorite-cast-bar.component';
import { MatIconModule } from '@angular/material/icon';
import { TvshowComponent } from './details/tvshow-details/tvshow.component';
import { EpisodeListComponent } from './details/tvshow-details/episode-list/episode-list.component';
import { CastListComponent } from './details/cast-list/cast-list.component';
import { CommentsComponent } from './details/comments/comments.component';

@NgModule({
    declarations: [
        AppComponent,
        TvshowComponent,
        EpisodeListComponent,
        CastListComponent,
        CommentsComponent,
        MovieComponent,
        FavoriteCastBarComponent,
    ],
    imports: [
        FormsModule,
        MatIconModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        SharedModule,
        MoviesModule,
        TvShowsModule,
        FriendsModule,
        HomeModule,
        AuthenticationModule,
        CoreModule,
        AppRoutingModule,
        MatDialogModule,
        HttpClientModule,
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
        },
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}
