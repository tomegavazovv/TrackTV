package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.UserWatchedEpisode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserWatchedEpisodeRepository : JpaRepository<UserWatchedEpisode, Long>