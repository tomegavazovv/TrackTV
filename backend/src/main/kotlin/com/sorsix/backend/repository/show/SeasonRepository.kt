package com.sorsix.backend.repository.show

import com.sorsix.backend.domain.show.Season
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SeasonRepository : JpaRepository<Season, Long>