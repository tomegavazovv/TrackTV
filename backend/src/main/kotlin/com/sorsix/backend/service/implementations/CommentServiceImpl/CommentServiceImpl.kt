package com.sorsix.backend.service.implementations.CommentServiceImpl

import com.sorsix.backend.dto.MovieCommentDto
import com.sorsix.backend.dto.ShowCommentDto
import com.sorsix.backend.service.interfaces.CommentService.CommentService
import com.sorsix.backend.service.interfaces.CommentService.MovieCommentService
import com.sorsix.backend.service.interfaces.CommentService.ShowCommentService
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val movieCommentService: MovieCommentService, private val showCommentService: ShowCommentService
) : CommentService {
    override fun commentMovie(userId: Long, movieId: Long, comment: String): List<MovieCommentDto> {
        return movieCommentService.commentMovie(userId, movieId, comment)
    }

    override fun getMovieComments(movieId: Long): List<MovieCommentDto> {
        return movieCommentService.getMovieComments(movieId)
    }

    override fun commentShow(userId: Long, showId: Long, comment: String): List<ShowCommentDto> {
        return showCommentService.commentShow(userId, showId, comment)
    }

    override fun getShowComments(showId: Long): List<ShowCommentDto> {
        return showCommentService.getShowComments(showId)
    }
}