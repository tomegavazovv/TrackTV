package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.TopFiveCastProjection
import com.sorsix.backend.domain.user.FavoriteShowCast
import com.sorsix.backend.domain.user.UserWatchShow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FavoriteShowCastRepository : JpaRepository<FavoriteShowCast, Long>{
    fun findByUserWatchShow(userWatchedShow: UserWatchShow): FavoriteShowCast?

    @Query(
        "SELECT c.id, c.role, c.name, c.image_url, count(uws.show_id) as show_count" +
                "FROM user_favorite_show_cast AS fs " +
                "LEFT JOIN user_watch_show AS uws ON fs.user_show_id = uws.id " +
                "LEFT JOIN tracktv_cast AS c ON fs.cast_id = c.id " +
                "WHERE uws.show_id = :showId " +
                "GROUP BY c.id, c.role, c.name, c.image_url " +
                "ORDER BY show_count DESC " +
                "LIMIT 5",
        nativeQuery = true
    )
    fun getTopFiveCastOfShow(showId: Long): List<TopFiveCastProjection>
}