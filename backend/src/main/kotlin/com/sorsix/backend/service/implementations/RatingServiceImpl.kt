package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.domain.user.RateMovie
import com.sorsix.backend.repository.user.RateEpisodeRepository
import com.sorsix.backend.repository.user.RateMovieRepository
import com.sorsix.backend.repository.user.WatchEpisodeRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.service.RatingService

class RatingServiceImpl(
    private val watchMovieRepository: WatchMovieRepository,
    private val rateMovieRepository: RateMovieRepository,
    private val rateEpisodeRepository: RateEpisodeRepository,
    private val watchEpisodeRepository: WatchEpisodeRepository
) : RatingService {
    override fun addMovieRating(userId: Long, movieId: Long, rating: Int, comment: String?): RateMovie? =
        watchMovieRepository.findByUserIdAndMovieId(userId, movieId)?.let {
            return if (rateMovieRepository.existsByWatchedMovie(it)) {
                val rateMovie = RateMovie(watchedMovie = it, rating = rating, comment = comment)
                rateMovieRepository.save(rateMovie)
            } else {
                null
            }
        }

    override fun getMovieRatingByUser(userId: Long, movieId: Long): RateMovie? =
        watchMovieRepository.findByUserIdAndMovieId(userId, movieId)?.let {
            rateMovieRepository.findByWatchedMovie(it)
        }

    override fun getMovieRatings(movieId: Long): List<RateMovie> =
        rateMovieRepository.findAllByWatchedMovie_MovieId(movieId.toInt())


    override fun rateEpisodeOfTvShow(userId: Long, episodeId: Long, rating: Int, comment: String?): RateEpisode? =
        watchEpisodeRepository.findByUserIdAndEpisodeId(userId, episodeId)?.let {
            return if (rateEpisodeRepository.existsByWatchedEpisode(it)) {
                val rateEpisode = RateEpisode(watchedEpisode = it, rating = rating, comment = comment)
                return rateEpisodeRepository.save(rateEpisode)
            } else {
                null
            }
        }

    override fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long): RateEpisode? =
        watchEpisodeRepository.findByUserIdAndEpisodeId(userId, showId)?.let {
            rateEpisodeRepository.findByWatchedEpisode(it)
        }

    override fun getRatingOfTvShowEpisode(episodeId: Long): List<RateEpisode> =
        rateEpisodeRepository.findAllByWatchedEpisode_EpisodeId(episodeId)

}