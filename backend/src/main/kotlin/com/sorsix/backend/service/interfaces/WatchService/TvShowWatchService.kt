package com.sorsix.backend.service.interfaces.WatchService

import com.sorsix.backend.domain.show.Episode
import com.sorsix.backend.domain.user.UserWatchShow
import com.sorsix.backend.domain.user.WatchEpisode
import com.sorsix.backend.dto.TvShowDto

interface TvShowWatchService {
    fun addWatchedTvShow(userId: Long, showId: Long): TvShowDto

    fun unwatchTvShow(userId: Long, showId: Long)

    fun getWatchedTvShows(userId: Long): List<UserWatchShow>

    fun unwatchEpisode(userId: Long, episodeId: Long)

    fun addWatchedEpisode(userId: Long, episodeId: Long): WatchEpisode

    fun getWatchedEpisodesOfShow(userId: Long, showId: Long): List<Episode>
}