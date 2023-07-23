package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.UserFavoriteShowCast
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserFavoriteShowCastRepository : JpaRepository<UserFavoriteShowCast, Long>