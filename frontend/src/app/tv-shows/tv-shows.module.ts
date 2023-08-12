import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TvShowDetailsComponent} from "./tvshow-details/tv-show-details.component";
import { YourShowsComponent } from './your-shows/your-shows.component';



@NgModule({
  declarations: [TvShowDetailsComponent, YourShowsComponent],
  imports: [
    CommonModule
  ]
})
export class TvShowsModule { }
