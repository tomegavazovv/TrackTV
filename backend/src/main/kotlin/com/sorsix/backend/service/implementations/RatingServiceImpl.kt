package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.domain.user.CommentMovie
import com.sorsix.backend.domain.user.RateMovie
import com.sorsix.backend.dto.AverageEpisodeRatingDto
import com.sorsix.backend.dto.MovieCommentDto
import com.sorsix.backend.exceptions.RatingNotFoundException
import com.sorsix.backend.exceptions.WatchedEpisodeNotFoundException
import com.sorsix.backend.exceptions.WatchedMovieNotFoundException
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.user.*
import com.sorsix.backend.service.RatingService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RatingServiceImpl(
    private val watchMovieRepository: WatchMovieRepository,
    private val commentMovieRepository: CommentMovieRepository,
    private val rateEpisodeRepository: RateEpisodeRepository,
    private val watchEpisodeRepository: WatchEpisodeRepository,
    private val userRepository: UserRepository,
    private val rateMovieRepository: RateMovieRepository
) : RatingService {
    override fun commentMovie(userId: Long, movieId: Long, comment: String): List<MovieCommentDto> {
        val user = userRepository.findById(userId).get()
        val watchedMovie =
            watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()
        val commentMovie = CommentMovie(watchedMovie = watchedMovie, comment = comment)

        commentMovieRepository.save(commentMovie)

        return commentMovieRepository.findAllByWatchedMovieMovieIdOrderByDateDesc(movieId.toInt())
            .map { MovieCommentDto(it.comment, it.watchedMovie.user.email, it.date) }
    }

    override fun getMovieCommentByUser(userId: Long, movieId: Long): CommentMovie {
        val watchedMovie =
            watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()

        return commentMovieRepository.findByWatchedMovie(watchedMovie) ?: throw RatingNotFoundException()
    }

    override fun rateMovie(userId: Long, movieId: Long, rating: Int) {
        val watchedMovie = watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()

        val rateMovie = rateMovieRepository.findByWatchedMovie(watchedMovie)?.let {
            RateMovie(it.id, it.watchedMovie, LocalDateTime.now(), rating)
        } ?: RateMovie(watchedMovie = watchedMovie, rating = rating)

        rateMovieRepository.save(rateMovie)
    }

    override fun getAverageMovieRating(movieId: Long): Double {
        val ratings = rateMovieRepository.findByWatchedMovieMovieId(movieId)
        if (ratings.isEmpty()) {
            return 0.0
        }

        val totalRating = ratings.sumOf { it.rating }.toDouble()
        return totalRating / ratings.size
    }

    override fun getMovieComments(movieId: Long): List<MovieCommentDto> =
        commentMovieRepository.findAllByWatchedMovieMovieIdOrderByDateDesc(movieId.toInt())
            .map { MovieCommentDto(it.comment, it.watchedMovie.user.email, it.date) }

    override fun rateEpisodeOfTvShow(userId: Long, episodeId: Long, rating: Int): AverageEpisodeRatingDto {
        val watchedEpisode = watchEpisodeRepository.findByUserIdAndEpisodeId(userId, episodeId)
            ?: throw WatchedEpisodeNotFoundException()

        val rateEpisode = rateEpisodeRepository.findByWatchedEpisodeUserIdAndWatchedEpisodeEpisodeId(userId, episodeId)?.let {
            RateEpisode(id=it.id, watchedEpisode = watchedEpisode, rating = rating)
        } ?: RateEpisode(watchedEpisode=watchedEpisode, rating = rating)

        rateEpisodeRepository.save(rateEpisode)
        val ratings = rateEpisodeRepository.findAllByWatchedEpisode_EpisodeId(episodeId)
        val avgRating = ratings.sumOf { it.rating } / ratings.size

        return AverageEpisodeRatingDto(avgRating)
    }

    override fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long): RateEpisode {
        val watchedEpisode =
            watchEpisodeRepository.findByUserIdAndEpisodeId(userId, showId) ?: throw WatchedEpisodeNotFoundException()

        return rateEpisodeRepository.findByWatchedEpisode(watchedEpisode) ?: throw RatingNotFoundException()
    }

    override fun getRatingOfTvShowEpisode(episodeId: Long): List<RateEpisode> =
        rateEpisodeRepository.findAllByWatchedEpisode_EpisodeId(episodeId)

}