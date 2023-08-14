package com.sorsix.backend.service.interfaces.WatchService

import com.sorsix.backend.domain.show.Episode
import com.sorsix.backend.domain.user.UserWatchShow
import com.sorsix.backend.domain.user.WatchEpisode
import com.sorsix.backend.domain.user.WatchMovie
import com.sorsix.backend.dto.TvShowDto

interface WatchService {
    fun addWatchedMovie(userId: Long, movieId: Long): WatchMovie

    fun unwatchMovie(userId: Long, movieId: Long)

    fun getWatchedMovies(userId: Long): List<WatchMovie>

    fun addWatchedTvShow(userId: Long, showId: Long): TvShowDto

    fun unwatchTvShow(userId: Long, showId: Long)

    fun unwatchEpisode(userId: Long, episodeId: Long)

    fun getWatchedTvShows(userId: Long): List<UserWatchShow>

    fun addWatchedEpisode(userId: Long, episodeId: Long): WatchEpisode

    fun getWatchedEpisodesOfShow(userId: Long, showId: Long): List<Episode>

    fun getRecentlyWatched(userId: Long): List<WatchMovie>

}