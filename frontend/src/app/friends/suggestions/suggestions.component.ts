import {Component, OnInit} from '@angular/core';
import {MovieTvService} from "../../services/movie-tv.service";
import {SuggestedMovie} from "../../interfaces/SuggestedMovie";
import {MatTableDataSource} from "@angular/material/table";
import {SuggestedShow} from "../../interfaces/SuggestedShow";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-suggestions',
  templateUrl: './suggestions.component.html',
  styleUrls: ['./suggestions.component.css']
})
export class SuggestionsComponent implements OnInit{
    suggestedMovies: SuggestedMovie[] = [];
    suggestedShows: SuggestedShow[] = [];

    displayedColumns: string[] = ['movieTitle', 'suggestedBy'];
    dataSource: MatTableDataSource<any> = new MatTableDataSource();

    constructor(private movieTvService: MovieTvService, private dialogRef: MatDialogRef<SuggestionsComponent>) {
    }
    ngOnInit(): void {
        this.fetchSuggestedMovies();
        this.fetchSuggestedShows();
        this.dataSource = new MatTableDataSource(this.suggestedMovies);
    }

    fetchSuggestedMovies(): void {
        this.movieTvService.getSuggestedMovies().subscribe({
            next: (data: SuggestedMovie[]) => {
                this.suggestedMovies = data;
                console.log(data[0])
            },
            error: (err) => {
                console.error(err);
            }
        })
    }
    fetchSuggestedShows(): void {
        this.movieTvService.getSuggestedShows().subscribe({
            next: (data) => {
                this.suggestedShows = data;
            },
            error: (err) => {
                console.error(err);
            }
        })
    }
    closeDialog(): void {
        this.dialogRef.close();
    }

}
