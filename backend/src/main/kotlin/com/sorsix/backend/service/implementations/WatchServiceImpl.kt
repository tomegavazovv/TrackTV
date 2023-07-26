package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.user.UserWatchShow
import com.sorsix.backend.domain.user.WatchedEpisode
import com.sorsix.backend.domain.user.WatchedMovie
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.movie.MovieRepository
import com.sorsix.backend.repository.show.EpisodeRepository
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.repository.user.WatchShowRepository
import com.sorsix.backend.repository.user.WatchEpisodeRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.service.WatchService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class WatchServiceImpl(
    private val watchMovieRepository: WatchMovieRepository,
    private val watchEpisodeRepository: WatchEpisodeRepository,
    private val watchShowRepository: WatchShowRepository,
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
    private val showRepository: ShowRepository,
    private val episodeRepository: EpisodeRepository
) : WatchService {
    override fun addWatchedMovie(userEmail: String, movieId: Long): WatchedMovie? {
        val user = userRepository.findByEmail(userEmail)
        val movie = movieRepository.findByIdOrNull(movieId)

        return movie?.let {
            watchMovieRepository.save(WatchedMovie(user = user, movie = movie))
        }
    }

    override fun getWatchedMovies(userId: Long): List<WatchedMovie> {
        return watchMovieRepository.findAllByUserId(userId)
    }

    override fun addWatchedTvShow(userEmail: String, showId: Long): UserWatchShow? {
        val user = userRepository.findByEmail(userEmail)
        val show = showRepository.findByIdOrNull(showId)

        return show?.let {
            watchShowRepository.save(UserWatchShow(user = user, show = show))
        }
    }

    override fun getWatchedTvShows(userId: Long): List<UserWatchShow> = watchShowRepository.findByUserId(userId)

    override fun addWatchedEpisode(userEmail: String, episodeId: Long): WatchedEpisode? {
        val user = userRepository.findByEmail(userEmail)
        val episode = episodeRepository.findByIdOrNull(episodeId)


        return episode?.let {
            val showId = it.show.id
            if(watchShowRepository.findByUserIdAndShowId(user.id!!, showId) == null) {
                watchShowRepository.save(UserWatchShow(user=user, show=episode.show))
            }
            watchEpisodeRepository.save(WatchedEpisode(user=user, episode = episode))
        }
    }

    override fun getRecentlyWatched(userId: Long): List<WatchedMovie> {
        return watchMovieRepository.findRecentlyWatchedByUser(userId)
    }

}