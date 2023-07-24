package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.user.UserRateEpisode
import com.sorsix.backend.domain.user.UserRateMovie
import com.sorsix.backend.service.RatingService

class RatingServiceImpl: RatingService {
    override fun addMovieRating(userId: Long, movieId: Long, rating: Int, comment: String): UserRateMovie {
        TODO("Not yet implemented")
    }

    override fun getMovieRatingByUser(userId: Long, movieId: Long): UserRateMovie {
        TODO("Not yet implemented")
    }

    override fun getMovieRatings(movieId: Long): List<UserRateMovie> {
        TODO("Not yet implemented")
    }

    override fun rateTvShowByEpisode(userId: Long, showId: Long, episodeId: Long, rating: Int): UserRateEpisode {
        TODO("Not yet implemented")
    }

    override fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long, ratingId: Long): UserRateEpisode {
        TODO("Not yet implemented")
    }

    override fun getRatingOfTvShowEpisode(showId: Long, ratingId: Long): List<UserRateEpisode> {
        TODO("Not yet implemented")
    }
}