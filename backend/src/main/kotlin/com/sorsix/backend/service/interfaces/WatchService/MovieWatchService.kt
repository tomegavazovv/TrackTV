package com.sorsix.backend.service.interfaces.WatchService

import com.sorsix.backend.domain.user.WatchMovie

interface MovieWatchService {
    fun addWatchedMovie(userId: Long, movieId: Long): WatchMovie
    fun unwatchMovie(userId: Long, movieId: Long)
    fun getWatchedMovies(userId: Long): List<WatchMovie>
    fun getRecentlyWatched(userId: Long): List<WatchMovie>
}