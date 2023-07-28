package com.sorsix.backend.service

import com.sorsix.backend.domain.movie.Movie

interface MovieService {

    fun searchByTitle(title: String): List<Movie>
}