package com.sorsix.backend.service

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.domain.user.RateMovie

interface RatingService {
    fun rateMovie(userId: Long, movieId: Long, rating: Int, comment: String?): RateMovie?

    fun getMovieRatingByUser(userId: Long, movieId: Long): RateMovie?

    fun getMovieRatings(movieId: Long): List<RateMovie>

    fun rateEpisodeOfTvShow(userId: Long, episodeId: Long, rating: Int, comment: String?): RateEpisode?

    fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long): RateEpisode?

    fun getRatingOfTvShowEpisode(episodeId: Long): List<RateEpisode>
}