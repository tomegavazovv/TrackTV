package com.sorsix.backend.service.interfaces.CastService

import com.sorsix.backend.domain.views.TopFiveCastOfMovieViewEntity
import com.sorsix.backend.domain.views.TopFiveCastOfShowViewEntity

interface TopCastService {
    fun getTopFiveCastsOfMovie(movieId: Long): List<TopFiveCastOfMovieViewEntity>
    fun getTopFiveCastsOfTvShow(showId: Long): List<TopFiveCastOfShowViewEntity>
}