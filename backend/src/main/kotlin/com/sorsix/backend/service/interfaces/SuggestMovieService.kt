package com.sorsix.backend.service.interfaces

import com.sorsix.backend.domain.movie.SuggestMovie
import com.sorsix.backend.dto.MovieSuggestionDto

interface SuggestMovieService {
    fun suggest(fromId: Long, toId: Long, movieId: Long): SuggestMovie

    fun getSuggestions(userId: Long): List<SuggestMovie>

    fun getSuggestionsByUserAndMovieId(userId: Long, movieId: Long): List<MovieSuggestionDto>

    fun deleteSuggestion(id: Long)
}