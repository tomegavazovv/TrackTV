package com.sorsix.backend.service.implementations.RatingServiceImpl

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.dto.AverageEpisodeRatingDto
import com.sorsix.backend.exceptions.RatingNotFoundException
import com.sorsix.backend.exceptions.WatchedEpisodeNotFoundException
import com.sorsix.backend.repository.user.RateEpisodeRepository
import com.sorsix.backend.repository.user.WatchEpisodeRepository
import com.sorsix.backend.service.interfaces.RatingService.ShowRatingService
import org.springframework.stereotype.Service

@Service
class ShowRatingServiceImpl(
    private val watchEpisodeRepository: WatchEpisodeRepository,
    private val rateEpisodeRepository: RateEpisodeRepository
): ShowRatingService {
    override fun rateEpisodeOfTvShow(userId: Long, episodeId: Long, rating: Int): AverageEpisodeRatingDto {
        val watchedEpisode = watchEpisodeRepository.findByUserIdAndEpisodeId(userId, episodeId)
            ?: throw WatchedEpisodeNotFoundException()

        val rateEpisode =
            rateEpisodeRepository.findByWatchEpisodeUserIdAndWatchEpisodeEpisodeId(userId, episodeId)?.let {
                RateEpisode(id = it.id, watchEpisode = watchedEpisode, rating = rating)
            } ?: RateEpisode(watchEpisode = watchedEpisode, rating = rating)

        rateEpisodeRepository.save(rateEpisode)
        val ratings = rateEpisodeRepository.findAllByWatchEpisode_EpisodeId(episodeId)
        val avgRating = ratings.sumOf { it.rating } / ratings.size

        return AverageEpisodeRatingDto(avgRating)
    }

    override fun getRatingOfTvShowEpisodeByUser(userId: Long, showId: Long): RateEpisode {
        val watchedEpisode =
            watchEpisodeRepository.findByUserIdAndEpisodeId(userId, showId) ?: throw WatchedEpisodeNotFoundException()

        return rateEpisodeRepository.findByWatchEpisode(watchedEpisode) ?: throw RatingNotFoundException()
    }

    override fun getRatingOfTvShowEpisode(episodeId: Long): List<RateEpisode> =
        rateEpisodeRepository.findAllByWatchEpisode_EpisodeId(episodeId)
}