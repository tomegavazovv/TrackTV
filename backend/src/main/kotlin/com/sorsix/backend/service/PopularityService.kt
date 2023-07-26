package com.sorsix.backend.service

import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.show.Show

interface PopularityService {
    fun getMostPopularMovies(): List<Movie>

    fun getMostPopularTvShows(): List<Show>

}