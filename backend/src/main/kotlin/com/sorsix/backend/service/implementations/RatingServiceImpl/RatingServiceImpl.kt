package com.sorsix.backend.service.implementations.RatingServiceImpl

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.dto.AverageEpisodeRatingDto
import com.sorsix.backend.service.interfaces.RatingService.MovieRatingService
import com.sorsix.backend.service.interfaces.RatingService.RatingService
import com.sorsix.backend.service.interfaces.RatingService.ShowRatingService
import org.springframework.stereotype.Service

@Service
class RatingServiceImpl(
    private val showRatingService: ShowRatingService,
    private val movieRatingService: MovieRatingService
) : RatingService {
    override fun rateMovie(userId: Long, movieId: Long, rating: Int) {
        movieRatingService.rateMovie(userId, movieId, rating)
    }

    override fun getAverageMovieRating(movieId: Long): Double {
        return movieRatingService.getAverageMovieRating(movieId)
    }

    override fun rateEpisodeOfTvShow(userId: Long, episodeId: Long, rating: Int): AverageEpisodeRatingDto {
        return showRatingService.rateEpisodeOfTvShow(userId, episodeId, rating)
    }

    override fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long): RateEpisode {
        return showRatingService.getRatingOfTvShowEpisodeByUser(userId, showId)
    }

    override fun getRatingOfTvShowEpisode(episodeId: Long): List<RateEpisode> {
        return showRatingService.getRatingOfTvShowEpisode(episodeId)
    }
}