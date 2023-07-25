package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.TopFiveCastProjection
import com.sorsix.backend.domain.user.FavoriteMovieCast
import com.sorsix.backend.domain.user.WatchedMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FavoriteMovieCastRepository : JpaRepository<FavoriteMovieCast, Long> {
    fun findByWatchedMovie(watchedMovie: WatchedMovie): FavoriteMovieCast?

    @Query(
        "SELECT c.id, c.role, c.name, c.image_url, COUNT(uwm.movie_id) AS movie_count " +
                "FROM user_favorite_movie_cast AS fm " +
                "LEFT JOIN user_watched_movie AS uwm ON fm.user_movie_id = uwm.id " +
                "LEFT JOIN tracktv_cast AS c ON fm.cast_id = c.id " +
                "WHERE uwm.movie_id = :movieId " +
                "GROUP BY c.id, c.role, c.name, c.image_url " +
                "ORDER BY movie_count DESC " +
                "LIMIT 5",
        nativeQuery = true
    )
    fun getTopFiveCastOfMovie(movieId: Long): List<TopFiveCastProjection>
}