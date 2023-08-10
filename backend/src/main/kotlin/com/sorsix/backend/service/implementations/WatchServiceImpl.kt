package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.show.Episode
import com.sorsix.backend.domain.user.UserWatchShow
import com.sorsix.backend.domain.user.WatchedEpisode
import com.sorsix.backend.domain.user.WatchedMovie
import com.sorsix.backend.exceptions.*
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.movie.MovieRepository
import com.sorsix.backend.repository.show.EpisodeRepository
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.repository.user.WatchEpisodeRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.repository.user.WatchShowRepository
import com.sorsix.backend.service.WatchService
import jakarta.transaction.Transactional
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
    override fun addWatchedMovie(userId: Long, movieId: Long): WatchedMovie? {
        val user = userRepository.findById(userId).orElse(null)
        val movie = movieRepository.findByIdOrNull(movieId)
            ?: throw MovieNotFoundException(movieId)

        return watchMovieRepository.save(WatchedMovie(user=user, movie=movie))
    }
    @Transactional
    override fun unwatchMovie(userId: Long, movieId: Long) {
        watchMovieRepository.findByUserIdAndMovieId(userId, movieId)?.let {
            watchMovieRepository.delete(it)
        } ?: throw WatchedMovieNotFoundException()
    }

    override fun getWatchedMovies(userId: Long): List<WatchedMovie> {
        return watchMovieRepository.findAllByUserId(userId)
    }

    override fun addWatchedTvShow(userId: Long, showId: Long): UserWatchShow? {
        val user = userRepository.findById(userId).orElse(null)
        val show = showRepository.findByIdOrNull(showId)
            ?: throw ShowNotFoundException(showId)

        return watchShowRepository.save(UserWatchShow(user = user, show = show))
    }

    override fun unwatchTvShow(userId: Long, showId: Long) {
        watchShowRepository.findByUserIdAndShowId(userId,showId)?.let {
            watchShowRepository.delete(it)
        } ?: throw WatchedShowNotFoundException()
    }

    override fun unwatchEpisode(userId: Long, episodeId: Long) {
        watchEpisodeRepository.findByUserIdAndEpisodeId(userId, episodeId)?.let {
            watchEpisodeRepository.delete(it)
        }
    }

    override fun getWatchedTvShows(userId: Long): List<UserWatchShow> = watchShowRepository.findByUserId(userId)

    override fun addWatchedEpisode(userId: Long, episodeId: Long): WatchedEpisode? {
        val user = userRepository.findById(userId).orElse(null)
        val episode = episodeRepository.findByIdOrNull(episodeId)
            ?: throw EpisodeNotFoundException(episodeId)

        val showId = episode.show.id

        if (watchShowRepository.findByUserIdAndShowId(user.id!!, showId) == null) {
            watchShowRepository.save(UserWatchShow(user = user, show = episode.show))
        }
        return watchEpisodeRepository.save(WatchedEpisode(user = user, episode = episode))
    }

    override fun getWatchedEpisodesOfShow(userId: Long, showId: Long): List<Episode> {
        return watchEpisodeRepository.findAllByEpisode_ShowIdAndUserId(showId, userId).map { it.episode }
    }

    override fun getRecentlyWatched(userId: Long): List<WatchedMovie> {
        return watchMovieRepository.findRecentlyWatchedByUser(userId)
    }

}