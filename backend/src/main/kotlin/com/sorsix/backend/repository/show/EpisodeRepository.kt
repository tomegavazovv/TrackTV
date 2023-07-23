package com.sorsix.backend.repository.show

import com.sorsix.backend.domain.show.Episode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EpisodeRepository : JpaRepository<Episode, Long>