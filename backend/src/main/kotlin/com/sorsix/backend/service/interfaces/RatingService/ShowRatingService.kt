package com.sorsix.backend.service.interfaces.RatingService

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.dto.AverageEpisodeRatingDto

interface ShowRatingService {

    fun rateEpisodeOfTvShow(userId: Long, episodeId: Long, rating: Int): AverageEpisodeRatingDto

    fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long): RateEpisode

    fun getRatingOfTvShowEpisode(episodeId: Long): List<RateEpisode>
}