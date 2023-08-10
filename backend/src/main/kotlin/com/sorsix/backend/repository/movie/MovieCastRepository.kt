package com.sorsix.backend.repository.movie

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.movie.MovieCast
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieCastRepository : JpaRepository<MovieCast, Long>{

    fun findAllByMovie(movie: Movie): List<MovieCast>
}