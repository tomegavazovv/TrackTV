package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.WatchedMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface WatchMovieRepository : JpaRepository<WatchedMovie, Long>{
    fun findByUserIdAndMovieId(userId: Long, movieId: Long): WatchedMovie?

    fun findAllByUserId(userId: Long): List<WatchedMovie>

    @Query("select * from user_watched_movie where user_id=:userId order by date desc limit 5", nativeQuery = true)
    fun findRecentlyWatchedByUser(userId: Long): List<WatchedMovie>
}