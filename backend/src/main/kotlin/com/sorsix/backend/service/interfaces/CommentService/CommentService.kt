package com.sorsix.backend.service.interfaces.CommentService

import com.sorsix.backend.domain.user.CommentMovie
import com.sorsix.backend.dto.MovieCommentDto
import com.sorsix.backend.dto.ShowCommentDto

interface CommentService {

    fun commentMovie(userId: Long, movieId: Long, comment: String): List<MovieCommentDto>

    fun getMovieComments(movieId: Long): List<MovieCommentDto>

    fun commentShow(userId: Long, showId: Long, comment: String): List<ShowCommentDto>

    fun getShowComments(showId: Long): List<ShowCommentDto>
}