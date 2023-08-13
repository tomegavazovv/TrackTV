package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.domain.user.WatchedEpisode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RateEpisodeRepository : JpaRepository<RateEpisode, Long>{
    fun findByWatchedEpisode(watchedEpisode: WatchedEpisode): RateEpisode?

    fun existsByWatchedEpisode(watchedEpisode: WatchedEpisode): Boolean

    fun findByWatchedEpisodeEpisodeId(episodeId: Long): RateEpisode?

    fun findByWatchedEpisodeUserIdAndWatchedEpisodeEpisodeId(userId: Long, episodeId: Long): RateEpisode?

    fun findAllByWatchedEpisode_EpisodeId(episodeId: Long): List<RateEpisode>
}