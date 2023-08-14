package com.sorsix.backend.service.implementations.CommentServiceImpl

import com.sorsix.backend.domain.user.CommentMovie
import com.sorsix.backend.dto.MovieCommentDto
import com.sorsix.backend.exceptions.WatchedMovieNotFoundException
import com.sorsix.backend.repository.user.CommentMovieRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.service.interfaces.CommentService.MovieCommentService
import org.springframework.stereotype.Service

@Service
class MovieCommentServiceImpl(
    private val watchMovieRepository: WatchMovieRepository,
    private val commentMovieRepository: CommentMovieRepository
) : MovieCommentService {
    override fun commentMovie(userId: Long, movieId: Long, comment: String): List<MovieCommentDto> {
        val watchedMovie =
            watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()

        val commentMovie = CommentMovie(watchMovie = watchedMovie, comment = comment)
        commentMovieRepository.save(commentMovie)

        return commentMovieRepository.findAllByWatchMovieMovieIdOrderByDateDesc(movieId.toInt())
            .map { MovieCommentDto(it.comment, it.watchMovie.user.email, it.date) }
    }

    override fun getMovieComments(movieId: Long): List<MovieCommentDto> =
        commentMovieRepository.findAllByWatchMovieMovieIdOrderByDateDesc(movieId.toInt())
            .map { MovieCommentDto(it.comment, it.watchMovie.user.email, it.date) }
}