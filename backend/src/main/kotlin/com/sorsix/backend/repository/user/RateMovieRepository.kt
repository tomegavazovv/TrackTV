package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.RateMovie
import com.sorsix.backend.domain.user.WatchedMovie
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RateMovieRepository : CrudRepository<RateMovie, Long> {
    fun findByWatchedMovie(watchedMovie: WatchedMovie): RateMovie?

    fun findByWatchedMovieMovieId(movieId: Long): List<RateMovie>

}