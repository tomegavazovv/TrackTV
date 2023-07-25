package com.sorsix.backend.service

import com.sorsix.backend.domain.user.UserWatchShow
import com.sorsix.backend.domain.user.WatchedEpisode
import com.sorsix.backend.domain.user.WatchedMovie

interface WatchService {
    fun addWatchedMovie(userEmail: String, movieId: Long): WatchedMovie?

    fun getWatchedMovies(userId: Long): List<WatchedMovie>

    fun addWatchedTvShow(userEmail: String, showId: Long): UserWatchShow?

    fun getWatchedTvShows(userId: Long): List<UserWatchShow>

    fun addWatchedEpisode(userEmail: String, episodeId: Long): WatchedEpisode?


}