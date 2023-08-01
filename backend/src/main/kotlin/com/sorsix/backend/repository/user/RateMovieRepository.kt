package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.RateMovie
import com.sorsix.backend.domain.user.WatchedMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RateMovieRepository : JpaRepository<RateMovie, Long>{
    fun existsByWatchedMovie(watchedMovie: WatchedMovie): Boolean

    fun findByWatchedMovie(watchedMovie: WatchedMovie): RateMovie?

    fun findAllByWatchedMovie_MovieId(movieId: Int): List<RateMovie>


}