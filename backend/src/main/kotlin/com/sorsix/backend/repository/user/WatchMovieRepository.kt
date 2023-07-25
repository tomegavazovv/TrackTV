package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.WatchedMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WatchMovieRepository : JpaRepository<WatchedMovie, Long>{
    fun findByUserIdAndMovieId(userId: Long, movieId: Long): WatchedMovie?

    fun findAllByUserId(userId: Long): List<WatchedMovie>
}