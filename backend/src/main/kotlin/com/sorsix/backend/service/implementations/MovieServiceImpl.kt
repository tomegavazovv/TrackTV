package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.dto.MovieDto
import com.sorsix.backend.exceptions.MovieNotFoundException
import com.sorsix.backend.repository.movie.MovieRepository
import com.sorsix.backend.repository.user.RateMovieRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.service.interfaces.MovieService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(
    private val movieRepository: MovieRepository,
    private val watchMovieRepository: WatchMovieRepository,
    private val rateMovieRepository: RateMovieRepository,
) : MovieService {

    override fun searchByTitle(title: String): List<Movie> {
        return movieRepository.searchByTitleContainingIgnoreCase(title)
    }

    override fun findById(userId: Long, movieId: Long): MovieDto {
        val movie = movieRepository.findByIdOrNull(movieId) ?: throw MovieNotFoundException(movieId)

        return watchMovieRepository.findByUserIdAndMovieId(userId, movieId)?.let {
            val rating = rateMovieRepository.findByWatchMovie(it)?.rating ?: 0
            MovieDto(movie, true, "movieDescription", rating)
        } ?: MovieDto(movie, false, "movieDescription", 0)
    }

    override fun getMostPopularMovies(): List<Movie> {
        return movieRepository.findMostPopular()
    }


}