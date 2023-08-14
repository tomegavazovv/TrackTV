package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.dto.EpisodeDto
import com.sorsix.backend.dto.TvShowDto
import com.sorsix.backend.exceptions.SeasonNotFoundException
import com.sorsix.backend.exceptions.ShowNotFoundException
import com.sorsix.backend.repository.show.EpisodeRepository
import com.sorsix.backend.repository.show.SeasonRepository
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.repository.user.RateEpisodeRepository
import com.sorsix.backend.repository.user.WatchEpisodeRepository
import com.sorsix.backend.repository.user.WatchShowRepository
import com.sorsix.backend.service.interfaces.TvShowService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TvShowServiceImpl(
    private val showRepository: ShowRepository,
    private val episodeRepository: EpisodeRepository,
    private val seasonRepository: SeasonRepository,
    private val watchEpisodeRepository: WatchEpisodeRepository,
    private val rateEpisodeRepository: RateEpisodeRepository,
    private val watchShowRepository: WatchShowRepository
) : TvShowService {

    override fun getById(userId: Long, id: Long): TvShowDto {
        val show = this.showRepository.findByIdOrNull(id) ?: throw ShowNotFoundException(id)

        return if (this.watchShowRepository.findByUserIdAndShowId(userId, id) != null) {
            TvShowDto(show, true)
        } else {
            TvShowDto(show, false)
        }
    }

    override fun getEpisodes(userId: Long, showId: Long, seasonNumber: Long): List<EpisodeDto> {
        val show = showRepository.findByIdOrNull(showId) ?: throw ShowNotFoundException(showId)
        val season = seasonRepository.findByIdOrNull(seasonNumber) ?: throw SeasonNotFoundException(seasonNumber)
        val watchedEpisodes = watchEpisodeRepository.findAllByEpisode_ShowIdAndUserId(showId, userId)

        return episodeRepository.findAllByShowAndSeason(show, season).map { ep ->
            val ratings = rateEpisodeRepository.findAllByWatchEpisode_EpisodeId(ep.id)
            val avgRating = if (ratings.isNotEmpty()) {
                ratings.sumOf { it.rating } / ratings.size
            } else {
                0
            }
            watchedEpisodes.find { watchedEp -> watchedEp.episode.id == ep.id }?.let {
                EpisodeDto(ep, watched = true, rateEpisodeRepository.findByWatchEpisode(it)?.rating ?: 0, avgRating)
            } ?: EpisodeDto(ep, false, 0, avgRating)
        }
    }

    override fun getMostPopularTvShows(): List<Show> {
        return showRepository.findMostPopular()
    }

    override fun searchByTitle(title: String): List<Show> {
        return showRepository.searchByTitleContainingIgnoreCase(title)
    }

}