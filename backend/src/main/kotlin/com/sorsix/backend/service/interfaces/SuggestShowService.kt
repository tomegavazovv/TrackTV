package com.sorsix.backend.service.interfaces

import com.sorsix.backend.domain.show.SuggestShow
import com.sorsix.backend.dto.ShowSuggestionDto

interface SuggestShowService {

    fun suggest(fromId: Long, toId: Long, showId: Long): SuggestShow

    fun getSuggestions(userId: Long): List<SuggestShow>

    fun getSuggestionsByUserAndShowId(userId: Long, showId: Long): List<ShowSuggestionDto>
    fun deleteSuggestion(id: Long)


}