package com.sorsix.backend.service.interfaces.CommentService

import com.sorsix.backend.dto.MovieCommentDto

interface MovieCommentService {
    fun commentMovie(userId: Long, movieId: Long, comment: String): List<MovieCommentDto>
    fun getMovieComments(movieId: Long): List<MovieCommentDto>
}