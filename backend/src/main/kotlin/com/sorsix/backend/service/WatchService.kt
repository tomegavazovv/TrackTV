package com.sorsix.backend.service

import com.sorsix.backend.domain.user.UserWatchShow
import com.sorsix.backend.domain.user.WatchedEpisode
import com.sorsix.backend.domain.user.WatchedMovie

interface WatchService {
    fun addWatchedMovie(userId: Long, movieId: Long): WatchedMovie?

    fun getWatchedMovies(userId: Long): List<WatchedMovie>

    fun addWatchedTvShow(userId: Long, showId: Long): UserWatchShow?

    fun getWatchedTvShows(userId: Long): List<UserWatchShow>

    fun addWatchedEpisode(userId: Long, episodeId: Long): WatchedEpisode?

    fun getRecentlyWatched(userId: Long): List<WatchedMovie>

}