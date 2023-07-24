package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.service.WatchService

class WatchServiceImpl: WatchService {
    override fun addWatchedMovie(userId: Long, movieId: Long): Movie {
        TODO("Not yet implemented")
    }

    override fun getWatchedMovies(userId: Long): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun addWatchedTvShow(userId: Long, showId: Long): Show {
        TODO("Not yet implemented")
    }

    override fun getWatchedTvShows(userId: Long): List<Show> {
        TODO("Not yet implemented")
    }
}