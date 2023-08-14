package com.sorsix.backend.service.interfaces.RatingService

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.domain.user.CommentMovie
import com.sorsix.backend.dto.AverageEpisodeRatingDto
import com.sorsix.backend.dto.MovieCommentDto

interface RatingService {

    fun rateMovie(userId: Long, movieId: Long, rating: Int)

    fun getAverageMovieRating(movieId: Long): Double


    fun rateEpisodeOfTvShow(userId: Long, episodeId: Long, rating: Int): AverageEpisodeRatingDto

    fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long): RateEpisode

    fun getRatingOfTvShowEpisode(episodeId: Long): List<RateEpisode>
}