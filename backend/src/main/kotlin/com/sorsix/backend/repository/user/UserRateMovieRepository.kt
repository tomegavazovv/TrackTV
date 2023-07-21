package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.UserRateMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRateMovieRepository : JpaRepository<UserRateMovie, Long>