package com.sorsix.backend.repository.show

import com.sorsix.backend.domain.show.Episode
import com.sorsix.backend.domain.show.Season
import com.sorsix.backend.domain.show.Show
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EpisodeRepository : JpaRepository<Episode, Long>{
    fun findAllByShowAndSeason(show: Show, season: Season): List<Episode>
}