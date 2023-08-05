import {NgModule} from '@angular/core';
import {Routes, RouterModule} from "@angular/router";
import {HomePageComponent} from "./pages/home-page/home-page.component";
import {RegisterComponent} from "./pages/register/register.component";

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
]

@NgModule({
    declarations: [],
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
