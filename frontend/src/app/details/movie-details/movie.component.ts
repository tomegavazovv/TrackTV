import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cast } from 'src/app/interfaces/Cast';
import { Movie } from '../../interfaces/movie';
import { MovieService } from './movie.service';
import { CastService } from '../cast.service';
import { CommentsService } from '../comments.service';
import { mergeMap, switchMap } from 'rxjs';

@Component({
    selector: 'app-movie',
    templateUrl: './movie.component.html',
    styleUrls: ['./movie.component.css'],
})
export class MovieComponent implements OnInit {
    movie: Movie | undefined;
    favoriteCast: Cast | undefined;
    castings: Cast[] = [];
    averageRating = 0;

    constructor(
        private movieService: MovieService,
        private castService: CastService,
        private commentsService: CommentsService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.route.url
            .pipe(
                switchMap((url) => {
                    const id = +url[1].path;
                    return this.movieService.getMovieById(id);
                })
            )
            .subscribe((movie) => {
                this.movie = movie;
                this.movieService.getAverageRating(movie.data.id);
            });

        this.castService.favoriteCast$.subscribe(
            (favCast) => (this.favoriteCast = favCast)
        );

        this.movieService.averageRating$.subscribe(
            (avg) => (this.averageRating = avg.valueOf())
        );
    }

    onVoteFavorite(castId: number) {
        if (this.movie) {
            this.castService.voteForFavoriteCastOfMovie(
                this.movie.data.id,
                castId
            );
        }
    }

    onNewComment(text: string) {
        if (this.movie) {
            this.commentsService.addCommentToMovie(this.movie.data.id, text);
        }
    }

    onAddToWatchedMovies() {
        if (this.movie && !this.movie.watched) {
            this.movieService.markMovieAsWatched(this.movie.data.id);
            this.movie.watched = true;
        }
    }

    onRateMovie(rating: number) {
        if (this.movie) {
            this.movieService.rateMovie(this.movie.data.id, rating);
        }
    }
}
