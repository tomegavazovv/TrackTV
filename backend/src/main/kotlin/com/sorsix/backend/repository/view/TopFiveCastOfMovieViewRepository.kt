package com.sorsix.backend.repository.view

import com.sorsix.backend.domain.views.TopFiveCastOfMovieViewEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TopFiveCastOfMovieViewRepository : JpaRepository<TopFiveCastOfMovieViewEntity, Long> {
    @Query(
        "SELECT c FROM TopFiveCastViewEntity c " +
                "JOIN user_watched_movie AS uwm ON c.id = uwm.cast_id " +
                "WHERE uwm.movie_id = :movieId " +
                "ORDER BY c.movieCount DESC"
    , nativeQuery = true)
    fun getTopFiveCastForMovie(@Param("movieId") movieId: Long): List<TopFiveCastOfMovieViewEntity>
}