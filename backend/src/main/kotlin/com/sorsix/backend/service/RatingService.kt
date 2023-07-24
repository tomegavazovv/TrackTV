package com.sorsix.backend.service

import com.sorsix.backend.domain.user.UserRateEpisode
import com.sorsix.backend.domain.user.UserRateMovie

interface RatingService {
    fun addMovieRating(userId: Long, movieId: Long, rating: Int, comment: String): UserRateMovie

    fun getMovieRatingByUser(userId: Long, movieId: Long): UserRateMovie

    fun getMovieRatings(movieId: Long): List<UserRateMovie>

    fun rateTvShowByEpisode(userId: Long, showId: Long, episodeId: Long, rating: Int): UserRateEpisode

    fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long, ratingId: Long): UserRateEpisode

    fun getRatingOfTvShowEpisode(showId: Long, ratingId: Long): List<UserRateEpisode>
}