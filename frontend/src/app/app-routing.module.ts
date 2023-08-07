import {NgModule} from '@angular/core';
import {Routes, RouterModule} from "@angular/router";
import {HomePageComponent} from "./pages/home-page/home-page.component";
import {RegisterComponent} from "./pages/register/register.component";
import {TvShowDetailsComponent} from "./tvshow-details/tv-show-details.component";
import {FriendsComponent} from "./friends/friends.component";

const routes: Routes = [
    {
        path: "",
        redirectTo: '/home',
        pathMatch: 'full'
    },
    {
        path: "home",
        component: HomePageComponent,
    },
    {
        path: "register",
        component: RegisterComponent
    },
    {
        path: 'show/:id',
        component: TvShowDetailsComponent
    },
    {
        path: 'friends',
        component: FriendsComponent
    }
]

@NgModule({
    declarations: [],
    imports: [RouterModule.forChild(routes), RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
