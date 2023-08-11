package com.sorsix.backend.service

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.domain.user.RateMovie
import com.sorsix.backend.dto.MovieRatingDto

interface RatingService {
    fun rateMovie(userId: Long, movieId: Long, rating: Int, comment: String?): List<MovieRatingDto>

    fun getMovieRatingByUser(userId: Long, movieId: Long): RateMovie

    fun getMovieRatings(movieId: Long): List<MovieRatingDto>

    fun rateEpisodeOfTvShow(userId: Long, episodeId: Long, rating: Int): RateEpisode

    fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long): RateEpisode

    fun getRatingOfTvShowEpisode(episodeId: Long): List<RateEpisode>
}