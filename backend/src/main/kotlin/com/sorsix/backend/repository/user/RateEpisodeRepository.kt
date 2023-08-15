package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.domain.user.WatchEpisode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RateEpisodeRepository : JpaRepository<RateEpisode, Long>{
    fun findByWatchEpisode(watchEpisode: WatchEpisode): RateEpisode?

    fun existsByWatchEpisode(watchEpisode: WatchEpisode): Boolean

    fun findByWatchEpisodeEpisodeId(episodeId: Long): RateEpisode?

    fun findByWatchEpisodeUserIdAndWatchEpisodeEpisodeId(userId: Long, episodeId: Long): RateEpisode?

    fun findAllByWatchEpisode_EpisodeId(episodeId: Long): List<RateEpisode>
}