package com.sorsix.backend.service.implementations.CastServiceImpl

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.exceptions.MovieNotFoundException
import com.sorsix.backend.exceptions.ShowNotFoundException
import com.sorsix.backend.repository.movie.MovieCastRepository
import com.sorsix.backend.repository.movie.MovieRepository
import com.sorsix.backend.repository.show.ShowCastRepository
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.service.interfaces.CastService.CastRetrievalService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CastRetrievalServiceImpl(
    private val movieRepository: MovieRepository,
    private val movieCastRepository: MovieCastRepository,
    private val showRepository: ShowRepository,
    private val showCastRepository: ShowCastRepository
) : CastRetrievalService {

    override fun getMovieCast(movieId: Long): List<Cast> {
        val movie = movieRepository.findByIdOrNull(movieId) ?: throw MovieNotFoundException(movieId)
        return movieCastRepository.findAllByMovie(movie).map { it.cast }
    }

    override fun getShowCast(showId: Long): List<Cast> {
        val show = showRepository.findByIdOrNull(showId) ?: throw ShowNotFoundException(showId)
        return showCastRepository.findByShow(show).map { it.cast }
    }
}