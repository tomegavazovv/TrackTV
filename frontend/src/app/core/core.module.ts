import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ErrorDialogComponent} from "./error-dialog/error-dialog.component";
import {NavigationBarComponent} from "./navigation-bar/navigation-bar.component";
import {FooterComponent} from "./footer/footer.component";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {RouterLink} from "@angular/router";
import {BrowserModule} from "@angular/platform-browser";


@NgModule({
    declarations: [ErrorDialogComponent, NavigationBarComponent, FooterComponent],
    exports: [
        ErrorDialogComponent,
        NavigationBarComponent,
        FooterComponent
    ],
    imports: [
        CommonModule,
        MatToolbarModule,
        MatButtonModule,
        RouterLink,
        BrowserModule
    ]
})
export class CoreModule { }
