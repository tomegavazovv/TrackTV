package com.sorsix.backend.service.implementations.WatchServiceImpl

import com.sorsix.backend.domain.show.Episode
import com.sorsix.backend.domain.user.UserWatchShow
import com.sorsix.backend.domain.user.WatchEpisode
import com.sorsix.backend.domain.user.WatchMovie
import com.sorsix.backend.dto.TvShowDto
import com.sorsix.backend.service.interfaces.WatchService.MovieWatchService
import com.sorsix.backend.service.interfaces.WatchService.TvShowWatchService
import com.sorsix.backend.service.interfaces.WatchService.WatchService
import org.springframework.stereotype.Service

@Service
class WatchServiceImpl(
    private val movieWatchService: MovieWatchService,
    private val tvShowWatchService: TvShowWatchService
) : WatchService {
    override fun addWatchedMovie(userId: Long, movieId: Long): WatchMovie {
        return movieWatchService.addWatchedMovie(userId, movieId)
    }

    override fun unwatchMovie(userId: Long, movieId: Long) {
        movieWatchService.unwatchMovie(userId, movieId)
    }

    override fun getWatchedMovies(userId: Long): List<WatchMovie> {
        return movieWatchService.getWatchedMovies(userId)
    }

    override fun addWatchedTvShow(userId: Long, showId: Long): TvShowDto {
        return tvShowWatchService.addWatchedTvShow(userId, showId)
    }

    override fun unwatchTvShow(userId: Long, showId: Long) {
        tvShowWatchService.unwatchTvShow(userId, showId)
    }

    override fun unwatchEpisode(userId: Long, episodeId: Long) {
        tvShowWatchService.unwatchEpisode(userId, episodeId)
    }

    override fun getWatchedTvShows(userId: Long): List<UserWatchShow> {
        return tvShowWatchService.getWatchedTvShows(userId)
    }

    override fun addWatchedEpisode(userId: Long, episodeId: Long): WatchEpisode {
        return tvShowWatchService.addWatchedEpisode(userId, episodeId)
    }

    override fun getWatchedEpisodesOfShow(userId: Long, showId: Long): List<Episode> {
        return tvShowWatchService.getWatchedEpisodesOfShow(userId, showId)
    }

    override fun getRecentlyWatched(userId: Long): List<WatchMovie> {
        return movieWatchService.getRecentlyWatched(userId)
    }

}