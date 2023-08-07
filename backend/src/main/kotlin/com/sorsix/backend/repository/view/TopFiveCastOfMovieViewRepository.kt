package com.sorsix.backend.repository.view

import com.sorsix.backend.domain.views.TopFiveCastOfMovieViewEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TopFiveCastOfMovieViewRepository : JpaRepository<TopFiveCastOfMovieViewEntity, Long> {
    @Query(
        "SELECT c.id, c.role, c.name, c.image_url, c.movie_count " +
                "FROM top_five_cast_of_movie_view c " +
                "JOIN user_watched_movie AS uwm ON c.id = uwm.movie_id " +
                "WHERE uwm.movie_id = :movieId " +
                "ORDER BY c.movie_count DESC"
        , nativeQuery = true)
    fun getTopFiveCastForMovie(@Param("movieId") movieId: Long): List<TopFiveCastOfMovieViewEntity>

    fun getTopFiveCastOfMovieViewEntitiesByMovieId(movieId: Long): List<TopFiveCastOfMovieViewEntity>
}