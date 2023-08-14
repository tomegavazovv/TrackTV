package com.sorsix.backend.service.interfaces.CastService

import com.sorsix.backend.domain.Cast


interface CastRetrievalService {
    fun getMovieCast(movieId: Long): List<Cast>
    fun getShowCast(showId: Long): List<Cast>
}