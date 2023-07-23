package com.sorsix.backend.repository.show

import com.sorsix.backend.domain.show.ShowCast
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShowCastRepository : JpaRepository<ShowCast, Long>