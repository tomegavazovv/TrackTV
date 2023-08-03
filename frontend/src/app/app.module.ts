import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { SearchBarComponent } from './pages/home-page/search-bar/search-bar.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RecentlyWatchedComponent } from './recently-watched/recently-watched.component';
import { TvshowComponent } from './pages/tvshow/tvshow.component';

@NgModule({
    declarations: [
        AppComponent,
        SearchBarComponent,
        HomePageComponent,
        RecentlyWatchedComponent,
        TvshowComponent,
    ],
    imports: [
        BrowserModule,
        RouterModule,
        HttpClientModule,
        ReactiveFormsModule,
    ],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {}
