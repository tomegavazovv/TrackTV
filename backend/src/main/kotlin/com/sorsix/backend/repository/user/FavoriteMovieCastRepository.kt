package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.FavoriteMovieCast
import com.sorsix.backend.domain.user.WatchMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FavoriteMovieCastRepository : JpaRepository<FavoriteMovieCast, Long> {
    fun findByWatchMovie(watchMovie: WatchMovie): FavoriteMovieCast?

    fun findByWatchMovieUserIdAndWatchMovieMovieId(userId: Long, movieId: Long): FavoriteMovieCast?
}