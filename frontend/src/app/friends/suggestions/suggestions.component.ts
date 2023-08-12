import {Component, OnInit} from '@angular/core';
import {WatchedMovie} from "../../interfaces/WatchedMovie";
import {PopularTvShow} from "../../interfaces/PopularTvShow";

@Component({
  selector: 'app-suggestions',
  templateUrl: './suggestions.component.html',
  styleUrls: ['./suggestions.component.css']
})
export class SuggestionsComponent implements OnInit{
    suggestedMovies: WatchedMovie[] = [];
    suggestedShows: PopularTvShow[] = [];

    constructor() {
    }
    ngOnInit(): void {
        this.fetchSuggestedMovies();
        this.fetchSuggestedShows();
    }

    fetchSuggestedMovies(): void {

    }
    fetchSuggestedShows(): void {

    }

}
