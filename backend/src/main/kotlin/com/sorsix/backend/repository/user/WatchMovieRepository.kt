package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.WatchMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface WatchMovieRepository : JpaRepository<WatchMovie, Long>{

    fun findByUserIdAndMovieId(userId: Long, movieId: Long): WatchMovie?

    fun findAllByUserId(userId: Long): List<WatchMovie>

    @Query("select * from user_watched_movie where user_id=:userId order by date desc limit 5", nativeQuery = true)
    fun findRecentlyWatchedByUser(userId: Long): List<WatchMovie>
}