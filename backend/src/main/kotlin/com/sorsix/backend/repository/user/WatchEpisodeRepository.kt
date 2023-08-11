package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.domain.user.WatchedEpisode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WatchEpisodeRepository : JpaRepository<WatchedEpisode, Long>{
    fun findByUserIdAndEpisodeId(userId: Long, episodeId: Long): WatchedEpisode?

    fun findAllByEpisode_ShowIdAndUserId(showId: Long, userId: Long): List<WatchedEpisode>

}