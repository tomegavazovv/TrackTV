import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Comment } from '../interfaces/Comment';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root',
})
export class CommentsService {
    constructor(private http: HttpClient) {}

    addCommentToShow(tvShowId: Number, comment: String): Observable<Comment[]> {
        return this.http.post<Comment[]>('/api/tvshows/comments', {
            showId: tvShowId,
            comment: comment,
        });
    }

    getCommentsOfShow(tvShowId: Number): Observable<Comment[]> {
        return this.http.get<Comment[]>(`/api/tvshows/comments/${tvShowId}`);
    }

    addCommentToMovie(movieId: Number, comment: string): Observable<Comment[]> {
        return this.http.post<Comment[]>('/api/movies/comment', {
            movieId,
            comment,
        });
    }

    getCommentsOfMovie(movieId: Number): Observable<Comment[]> {
        return this.http.get<Comment[]>(`/api/movies/comments/${movieId}`);
    }
}
