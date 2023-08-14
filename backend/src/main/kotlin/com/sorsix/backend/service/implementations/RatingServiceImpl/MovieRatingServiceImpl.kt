package com.sorsix.backend.service.implementations.RatingServiceImpl

import com.sorsix.backend.domain.user.RateMovie
import com.sorsix.backend.exceptions.WatchedMovieNotFoundException
import com.sorsix.backend.repository.user.RateMovieRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.service.interfaces.RatingService.MovieRatingService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MovieRatingServiceImpl(
    private val watchMovieRepository: WatchMovieRepository,
    private val rateMovieRepository: RateMovieRepository
): MovieRatingService {
    override fun rateMovie(userId: Long, movieId: Long, rating: Int) {
        val watchedMovie =
            watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()

        val rateMovie = rateMovieRepository.findByWatchMovie(watchedMovie)?.let {
            RateMovie(it.id, it.watchMovie, LocalDateTime.now(), rating)
        } ?: RateMovie(watchMovie = watchedMovie, rating = rating)

        rateMovieRepository.save(rateMovie)
    }

    override fun getAverageMovieRating(movieId: Long): Double {
        val ratings = rateMovieRepository.findByWatchMovieMovieId(movieId)
        if (ratings.isEmpty()) {
            return 0.0
        }

        val totalRating = ratings.sumOf { it.rating }.toDouble()
        return totalRating / ratings.size
    }
}