package com.sorsix.backend.service.implementations.WatchServiceImpl

import com.sorsix.backend.domain.show.Episode
import com.sorsix.backend.domain.user.UserWatchShow
import com.sorsix.backend.domain.user.WatchEpisode
import com.sorsix.backend.dto.TvShowDto
import com.sorsix.backend.dto.WatchedShowDto
import com.sorsix.backend.exceptions.EpisodeNotFoundException
import com.sorsix.backend.exceptions.ShowNotFoundException
import com.sorsix.backend.exceptions.WatchedShowNotFoundException
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.show.EpisodeRepository
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.repository.user.WatchEpisodeRepository
import com.sorsix.backend.repository.user.WatchShowRepository
import com.sorsix.backend.service.interfaces.WatchService.TvShowWatchService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TvShowWatchServiceImpl(
    private val userRepository: UserRepository,
    private val showRepository: ShowRepository,
    private val watchShowRepository: WatchShowRepository,
    private val watchEpisodeRepository: WatchEpisodeRepository,
    private val episodeRepository: EpisodeRepository
): TvShowWatchService {

    override fun addWatchedTvShow(userId: Long, showId: Long): TvShowDto {
        val user = userRepository.findById(userId).orElse(null)
        val show = showRepository.findByIdOrNull(showId) ?: throw ShowNotFoundException(showId)
        val uws = watchShowRepository.findByUserIdAndShowId(userId, showId)
            ?: watchShowRepository.save(UserWatchShow(user = user, show = show))

        return TvShowDto(uws.show, true)

    }

    @Transactional
    override fun unwatchTvShow(userId: Long, showId: Long) {
        watchShowRepository.findByUserIdAndShowId(userId, showId)?.let {
            watchShowRepository.delete(it)
        } ?: throw WatchedShowNotFoundException()
    }

    override fun unwatchEpisode(userId: Long, episodeId: Long) {
        watchEpisodeRepository.findByUserIdAndEpisodeId(userId, episodeId)?.let {
            watchEpisodeRepository.delete(it)
        }
    }

    override fun getWatchedTvShows(userId: Long): List<WatchedShowDto> =
        watchShowRepository.findByUserId(userId).map { WatchedShowDto(it.show) }

    override fun addWatchedEpisode(userId: Long, episodeId: Long): WatchEpisode {
        val user = userRepository.findById(userId).orElse(null)
        val episode = episodeRepository.findByIdOrNull(episodeId) ?: throw EpisodeNotFoundException(episodeId)

        val showId = episode.show.id

        if (watchShowRepository.findByUserIdAndShowId(user.id!!, showId) == null) {
            watchShowRepository.save(UserWatchShow(user = user, show = episode.show))
        }
        return watchEpisodeRepository.save(WatchEpisode(user = user, episode = episode))
    }

    override fun getWatchedEpisodesOfShow(userId: Long, showId: Long): List<Episode> {
        return watchEpisodeRepository.findAllByEpisode_ShowIdAndUserId(showId, userId).map { it.episode }
    }

}