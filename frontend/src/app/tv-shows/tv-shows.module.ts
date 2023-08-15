import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { YourShowsComponent } from './your-shows/your-shows.component';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { RouterLink } from '@angular/router';
import { SharedModule } from '../shared/shared.module';

@NgModule({
    declarations: [YourShowsComponent],
    imports: [
        CommonModule,
        SharedModule,
        MatButtonModule,
        MatTableModule,
        RouterLink,
    ],
})
export class TvShowsModule {}
