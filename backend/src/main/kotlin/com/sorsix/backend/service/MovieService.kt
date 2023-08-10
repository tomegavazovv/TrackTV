package com.sorsix.backend.service

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.dto.MovieDto

interface MovieService {

    fun searchByTitle(title: String): List<Movie>

    fun findById(userId: Long, movieId: Long): MovieDto

    fun getCast(movieId: Long): List<Cast>
}