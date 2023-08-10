import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Comment } from '../interfaces/Comment';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root',
})
export class CommentsService {
    comments = new Subject<Comment[]>();
    token =
        'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b21lZ2F2YXpvdiIsImV4cCI6MTY5Mzk5MDAyMSwiaWF0IjoxNjkxMzk4MDIxLCJ1c2VySWQiOjEsImVtYWlsIjoidG9tZWdhdmF6b3ZAZ21haWwuY29tIn0.vrc4jz_TgDHwPgfEJT74EpvwHQsen_mAe8BF5xTyDAQ';

    constructor(private http: HttpClient) {}

    addCommentToShow(tvShowId: Number, comment: String) {
        this.http
            .post<Comment[]>(
                '/api/tvshows/comments',
                {
                    showId: 1,
                    comment: comment,
                },
                {
                    headers: {
                        Authorization: this.token,
                    },
                }
            )
            .subscribe((comments) => {
                console.log(comments);
                this.comments.next(comments);
            });
    }

    getCommentsOfShow(tvShowId: Number) {
        this.http
            .get<Comment[]>('/api/tvshows/comments/1', {
                headers: {
                    Authorization: this.token,
                },
            })
            .subscribe((comments) => this.comments.next(comments));
    }

    addRatingToMovie(movieId: Number, rating: Number, comment?: String) {
        this.http
            .post<Comment[]>(
                '/api/movies/rate',
                {
                    movieId: 1,
                    rating: 10,
                    comment: comment,
                },
                {
                    headers: {
                        Authorization: this.token,
                    },
                }
            )
            .subscribe((comments) => {
                this.comments.next(comments);
            });
    }

    getRatingsOfMovie(movieId: Number) {
        this.http
            .get<Comment[]>('/api/movies/ratings/1', {
                headers: {
                    Authorization: this.token,
                },
            })
            .subscribe((comments) => {
                console.log('getRatings');
                this.comments.next(comments);
            });
    }
}
