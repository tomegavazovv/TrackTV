package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.CommentMovie
import com.sorsix.backend.domain.user.WatchedMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentMovieRepository : JpaRepository<CommentMovie, Long>{
    fun existsByWatchedMovie(watchedMovie: WatchedMovie): Boolean

    fun findByWatchedMovie(watchedMovie: WatchedMovie): CommentMovie?

    fun findAllByWatchedMovieMovieIdOrderByDateDesc(movieId: Int): List<CommentMovie>


}