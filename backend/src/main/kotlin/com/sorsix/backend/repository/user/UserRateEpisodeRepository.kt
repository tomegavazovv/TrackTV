package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.UserRateEpisode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRateEpisodeRepository : JpaRepository<UserRateEpisode, Long>