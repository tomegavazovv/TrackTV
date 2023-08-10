package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.domain.user.RateMovie
import com.sorsix.backend.dto.MovieRatingDto
import com.sorsix.backend.exceptions.RatingNotFoundException
import com.sorsix.backend.exceptions.WatchedEpisodeNotFoundException
import com.sorsix.backend.exceptions.WatchedMovieNotFoundException
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.user.RateEpisodeRepository
import com.sorsix.backend.repository.user.RateMovieRepository
import com.sorsix.backend.repository.user.WatchEpisodeRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.service.RatingService
import org.springframework.stereotype.Service

@Service
class RatingServiceImpl(
    private val watchMovieRepository: WatchMovieRepository,
    private val rateMovieRepository: RateMovieRepository,
    private val rateEpisodeRepository: RateEpisodeRepository,
    private val watchEpisodeRepository: WatchEpisodeRepository,
    private val userRepository: UserRepository
) : RatingService {
    override fun rateMovie(userId: Long, movieId: Long, rating: Int, comment: String?): List<MovieRatingDto> {
        val user = userRepository.findById(userId).get()
        val watchedMovie =
            watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()
        val rateMovie = RateMovie(watchedMovie = watchedMovie, rating = rating, comment = comment)

        rateMovieRepository.save(rateMovie)

        return rateMovieRepository.findAllByWatchedMovieMovieId(movieId.toInt())
            .map { MovieRatingDto(rating, comment, user.email) }
    }

    override fun getMovieRatingByUser(userId: Long, movieId: Long): RateMovie? {
        val watchedMovie =
            watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()

        return rateMovieRepository.findByWatchedMovie(watchedMovie) ?: throw RatingNotFoundException()
    }

    override fun getMovieRatings(movieId: Long): List<MovieRatingDto> =
        rateMovieRepository.findAllByWatchedMovieMovieId(movieId.toInt())
            .map { MovieRatingDto(it.rating, it.comment, it.watchedMovie.user.email) }

    override fun rateEpisodeOfTvShow(userId: Long, episodeId: Long, rating: Int): RateEpisode? {
        val watchedEpisode = watchEpisodeRepository.findByUserIdAndEpisodeId(userId, episodeId)
            ?: throw WatchedEpisodeNotFoundException()

        val rateEpisode = RateEpisode(watchedEpisode = watchedEpisode, rating = rating)
        return rateEpisodeRepository.save(rateEpisode)
    }

    override fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long): RateEpisode? {
        val watchedEpisode =
            watchEpisodeRepository.findByUserIdAndEpisodeId(userId, showId) ?: throw WatchedEpisodeNotFoundException()

        return rateEpisodeRepository.findByWatchedEpisode(watchedEpisode) ?: throw RatingNotFoundException()
    }

    override fun getRatingOfTvShowEpisode(episodeId: Long): List<RateEpisode> =
        rateEpisodeRepository.findAllByWatchedEpisode_EpisodeId(episodeId)

}