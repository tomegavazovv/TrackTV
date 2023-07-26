package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.repository.movie.MovieRepository
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.service.PopularityService
import org.springframework.stereotype.Service

@Service
class PopularityServiceImpl(private val movieRepository: MovieRepository, private val showRepository: ShowRepository) : PopularityService {
    override fun getMostPopularMovies(): List<Movie> {
        return movieRepository.findMostPopular()
    }

    override fun getMostPopularTvShows(): List<Show> {
        return showRepository.findMostPopular()
    }

}