import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TvShowDetailsComponent} from "./tvshow-details/tv-show-details.component";
import { YourShowsComponent } from './your-shows/your-shows.component';
import {MatButtonModule} from "@angular/material/button";
import {MatTableModule} from "@angular/material/table";



@NgModule({
  declarations: [TvShowDetailsComponent, YourShowsComponent],
    imports: [
        CommonModule,
        MatButtonModule,
        MatTableModule
    ]
})
export class TvShowsModule { }
