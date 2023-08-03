import { Component, OnInit } from '@angular/core';
import { TvShow } from 'src/app/interfaces/TvShow';
import { TvshowServiceService } from './tvshow-service.service';

@Component({
    selector: 'app-tvshow',
    templateUrl: './tvshow.component.html',
    styleUrls: ['./tvshow.component.css'],
})
export class TvshowComponent implements OnInit {
    tvShow: TvShow | undefined;

    constructor(private tvShowService: TvshowServiceService) {}

    ngOnInit() {
        this.tvShowService
            .getTvShow(1)
            .subscribe((tvShow) => (this.tvShow = tvShow.results));
    }
}
