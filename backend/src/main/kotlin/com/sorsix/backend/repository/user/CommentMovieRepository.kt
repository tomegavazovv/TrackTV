package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.user.CommentMovie
import com.sorsix.backend.domain.user.WatchMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentMovieRepository : JpaRepository<CommentMovie, Long>{
    fun findAllByWatchMovieMovieIdOrderByDateDesc(movieId: Int): List<CommentMovie>
}