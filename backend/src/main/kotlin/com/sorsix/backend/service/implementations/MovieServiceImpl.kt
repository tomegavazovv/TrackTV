package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.dto.MovieDto
import com.sorsix.backend.exceptions.MovieNotFoundException
import com.sorsix.backend.exceptions.ShowNotFoundException
import com.sorsix.backend.repository.movie.MovieCastRepository
import com.sorsix.backend.repository.movie.MovieRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.service.MovieService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(private val movieRepository: MovieRepository, private val movieCastRepository: MovieCastRepository, private val watchMovieRepository: WatchMovieRepository):MovieService {
    override fun searchByTitle(title: String): List<Movie> {
        return movieRepository.searchByTitleContainingIgnoreCase(title)
    }

    override fun findById(userId: Long, movieId: Long): MovieDto {
        val movie = movieRepository.findByIdOrNull(movieId) ?: throw MovieNotFoundException(movieId)
        var watched = false
        watchMovieRepository.findByUserIdAndMovieId(userId, movieId)?.let { watched = true }

        return MovieDto(movie, watched)
    }

    override fun getCast(movieId: Long): List<Cast> {
        val movie = movieRepository.findByIdOrNull(movieId)
            ?: throw MovieNotFoundException(movieId)

        return movieCastRepository.findAllByMovie(movie)
            .map { it.cast }
    }
}