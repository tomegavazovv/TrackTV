package com.sorsix.backend.service.interfaces

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.dto.MovieDto

interface MovieService {

    fun searchByTitle(title: String): List<Movie>

    fun findById(userId: Long, movieId: Long): MovieDto

    fun getMostPopularMovies(): List<Movie>


}