package com.sorsix.backend.service

import com.sorsix.backend.domain.show.Episode
import com.sorsix.backend.domain.user.UserWatchShow
import com.sorsix.backend.domain.user.WatchedEpisode
import com.sorsix.backend.domain.user.WatchedMovie

interface WatchService {
    fun addWatchedMovie(userId: Long, movieId: Long): WatchedMovie

    fun unwatchMovie(userId: Long, movieId: Long)

    fun getWatchedMovies(userId: Long): List<WatchedMovie>

    fun addWatchedTvShow(userId: Long, showId: Long): UserWatchShow

    fun unwatchTvShow(userId: Long, movieId: Long)

    fun unwatchEpisode(userId: Long, episodeId: Long)

    fun getWatchedTvShows(userId: Long): List<UserWatchShow>

    fun addWatchedEpisode(userId: Long, episodeId: Long): WatchedEpisode

    fun getWatchedEpisodesOfShow(userId: Long, showId: Long): List<Episode>

    fun getRecentlyWatched(userId: Long): List<WatchedMovie>

}