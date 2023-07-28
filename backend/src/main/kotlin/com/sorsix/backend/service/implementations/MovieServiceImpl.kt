package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.repository.movie.MovieRepository
import com.sorsix.backend.service.MovieService
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(private val movieRepository: MovieRepository):MovieService {
    override fun searchByTitle(title: String): List<Movie> {
        return movieRepository.searchByTitleContainingIgnoreCase(title)
    }
}