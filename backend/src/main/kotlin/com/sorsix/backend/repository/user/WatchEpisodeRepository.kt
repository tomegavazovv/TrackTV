package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.WatchEpisode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WatchEpisodeRepository : JpaRepository<WatchEpisode, Long>{
    fun findByUserIdAndEpisodeId(userId: Long, episodeId: Long): WatchEpisode?

    fun findAllByEpisode_ShowIdAndUserId(showId: Long, userId: Long): List<WatchEpisode>

}