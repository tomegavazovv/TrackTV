package com.sorsix.backend.service.interfaces.WatchService

import com.sorsix.backend.domain.user.WatchMovie
import com.sorsix.backend.dto.WatchedMovieDto

interface MovieWatchService {
    fun addWatchedMovie(userId: Long, movieId: Long): WatchMovie
    fun unwatchMovie(userId: Long, movieId: Long)
    fun getWatchedMovies(userId: Long): List<WatchedMovieDto>
    fun getRecentlyWatched(userId: Long): List<WatchMovie>
}