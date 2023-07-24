package com.sorsix.backend.service

import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.show.Show

interface WatchService {
    fun addWatchedMovie(userId: Long, movieId: Long): Movie

    fun getWatchedMovies(userId: Long): List<Movie>

    fun addWatchedTvShow(userId: Long, showId: Long): Show

    fun getWatchedTvShows(userId: Long): List<Show>
}