import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Comment } from 'src/app/interfaces/Comment';
import { CommentsService } from '../../comments.service';
import { Observable, switchMap } from 'rxjs';

@Component({
    selector: 'app-comments',
    templateUrl: './comments.component.html',
    styleUrls: ['./comments.component.css'],
})
export class CommentsComponent implements OnInit {
    comment = '';
    comments: Comment[] = [];
    commentsLoading: Boolean = false;
    isTvShow = false;

    constructor(
        private commentsService: CommentsService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.commentsLoading = true;
        this.route.url
            .pipe(
                switchMap((url) => {
                    this.isTvShow = url[0].path === 'tvshows';
                    return this.loadCommentsData(+url[1].path);
                })
            )
            .subscribe((comments) => {
                this.comments = comments;
                this.commentsLoading = false;
            });
    }

    private loadCommentsData(id: Number): Observable<Comment[]> {
        if (this.isTvShow) {
            return this.commentsService.getCommentsOfShow(id);
        } else {
            return this.commentsService.getCommentsOfMovie(id);
        }
    }

    onPostComment() {
        if (this.comment && this.comment.trim() !== '') {
            const id = +this.route.snapshot.url[1].path;
            if (this.isTvShow) {
                this.commentsService
                    .addCommentToShow(id, this.comment)
                    .subscribe((comments) => (this.comments = comments));
            } else {
                this.commentsService
                    .addCommentToMovie(id, this.comment)
                    .subscribe((comments) => (this.comments = comments));
            }
            this.comment = '';
        }
    }
}
