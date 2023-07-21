package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.UserWatchedMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserWatchedMovieRepository : JpaRepository<UserWatchedMovie, Long>