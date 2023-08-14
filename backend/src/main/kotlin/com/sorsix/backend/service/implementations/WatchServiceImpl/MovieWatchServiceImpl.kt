package com.sorsix.backend.service.implementations.WatchServiceImpl

import com.sorsix.backend.domain.user.WatchMovie
import com.sorsix.backend.dto.MovieDto
import com.sorsix.backend.dto.WatchedMovieDto
import com.sorsix.backend.exceptions.MovieNotFoundException
import com.sorsix.backend.exceptions.WatchedMovieNotFoundException
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.movie.MovieRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.service.interfaces.WatchService.MovieWatchService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MovieWatchServiceImpl(
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository,
    private val watchMovieRepository: WatchMovieRepository
): MovieWatchService {

    override fun addWatchedMovie(userId: Long, movieId: Long): WatchMovie {
        val user = userRepository.findById(userId).orElse(null)
        val movie = movieRepository.findByIdOrNull(movieId) ?: throw MovieNotFoundException(movieId)

        return watchMovieRepository.save(WatchMovie(user = user, movie = movie))
    }

    @Transactional
    override fun unwatchMovie(userId: Long, movieId: Long) {
        watchMovieRepository.findByUserIdAndMovieId(userId, movieId)?.let {
            watchMovieRepository.delete(it)
        } ?: throw WatchedMovieNotFoundException()
    }

    override fun getWatchedMovies(userId: Long): List<WatchedMovieDto> {
        return watchMovieRepository.findAllByUserId(userId).map {
            WatchedMovieDto(it.movie, it.date)
        }
    }

    override fun getRecentlyWatched(userId: Long): List<WatchMovie> {
        return watchMovieRepository.findRecentlyWatchedByUser(userId)
    }
}