package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.UserFavoriteMovieCast
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserFavoriteMovieCastRepository : JpaRepository<UserFavoriteMovieCast, Long>