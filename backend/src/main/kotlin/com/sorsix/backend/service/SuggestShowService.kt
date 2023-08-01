package com.sorsix.backend.service

import com.sorsix.backend.domain.show.SuggestShow

interface SuggestShowService {

    fun suggest(fromId: Long, toId: Long, showId: Long): SuggestShow

    fun getSuggestions(userId: Long): List<SuggestShow>

}