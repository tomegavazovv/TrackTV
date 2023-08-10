import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TvshowServiceService } from '../tvshow-service.service';
import { Comment } from 'src/app/interfaces/Comment';
import { MovieService } from '../../movie/movie.service';
import { ActivatedRoute } from '@angular/router';
import { CommentsService } from '../../comments.service';

@Component({
    selector: 'app-comments',
    templateUrl: './comments.component.html',
    styleUrls: ['./comments.component.css'],
})
export class CommentsComponent implements OnInit {
    comment = '';
    comments: Comment[] = [];

    constructor(
        private movieService: MovieService,
        private tvShowService: TvshowServiceService,
        private commentsService: CommentsService,
        private route: ActivatedRoute
    ) {}

    ngOnInit() {
        this.route.url.subscribe((url) => {
            if (url[0].path == 'tvshows') {
                this.commentsService.comments.subscribe(
                    (comments) => (this.comments = comments)
                );
            } else {
                this.commentsService.comments.subscribe(
                    (comments) => (this.comments = comments)
                );
            }
        });
    }

    onPostComment() {
        if (this.comment && this.comment.trim() !== '') {
            const urls = this.route.snapshot.url;
            if (urls[0].path == 'movies') {
                this.commentsService.addRatingToMovie(
                    +urls[1].path,
                    10,
                    this.comment
                );
            } else {
                this.commentsService.addCommentToShow(
                    +urls[1].path,
                    this.comment
                );
            }
            this.comment = '';
        }
    }
}
