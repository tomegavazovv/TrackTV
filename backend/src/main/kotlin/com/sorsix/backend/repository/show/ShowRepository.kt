package com.sorsix.backend.repository.show

import com.sorsix.backend.domain.show.Show
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShowRepository : JpaRepository<Show, Long>