import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cast } from 'src/app/interfaces/Cast';
import { Movie } from '../../interfaces/Movie';
import { MovieService } from './movie.service';
import { CastService } from '../cast.service';
import { CommentsService } from '../comments.service';
import { filter, map, switchMap, take } from 'rxjs';

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
    loading = false;

    constructor(
        private movieService: MovieService,
        private castService: CastService,
        private commentsService: CommentsService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.loading = true;
        this.route.paramMap
            .pipe(
                take(1),
                filter((paramMap) => paramMap.has('id')),
                map((paramMap) => +paramMap.get('id')!),
                switchMap((id) => {
                    return this.movieService.getMovieById(id);
                })
            )
            .subscribe((movie) => {
                this.movie = movie;
                this.movieService.getAverageRating(movie.data.id);
                this.loading = false;
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
