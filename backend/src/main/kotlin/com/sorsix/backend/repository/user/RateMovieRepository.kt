package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.RateMovie
import com.sorsix.backend.domain.user.WatchMovie
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RateMovieRepository : CrudRepository<RateMovie, Long> {
    fun findByWatchMovie(watchMovie: WatchMovie): RateMovie?

    fun findByWatchMovieMovieId(movieId: Long): List<RateMovie>

}