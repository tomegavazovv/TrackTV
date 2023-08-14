import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StarRatingComponent } from './star-rating/star-rating.component';
import { MatIconModule } from '@angular/material/icon';
import { SpinnerComponent } from './spinner/spinner.component';

@NgModule({
    declarations: [StarRatingComponent, SpinnerComponent],
    exports: [StarRatingComponent, SpinnerComponent],
    imports: [CommonModule, MatIconModule],
})
export class SharedModule {}
