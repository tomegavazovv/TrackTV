package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.FavoriteShowCast
import com.sorsix.backend.domain.user.UserWatchShow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FavoriteShowCastRepository : JpaRepository<FavoriteShowCast, Long>{
    fun findByUserWatchShow(userWatchedShow: UserWatchShow): FavoriteShowCast?

    fun findByUserWatchShow_UserIdAndUserWatchShow_ShowId(userId: Long, showId: Long): FavoriteShowCast?
}