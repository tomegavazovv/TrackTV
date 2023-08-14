package com.sorsix.backend.service.interfaces

import com.sorsix.backend.domain.movie.SuggestMovie

interface SuggestMovieService {
    fun suggest(fromId: Long, toId: Long, movieId: Long): SuggestMovie

    fun getSuggestions(userId: Long): List<SuggestMovie>
}