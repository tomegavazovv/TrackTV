package com.sorsix.backend.service.interfaces.CastService

import com.sorsix.backend.domain.Cast

interface FavoriteCastService {
    fun addFavoriteCastOfMovie(userId: Long, movieId: Long, castId: Long): Cast
    fun getFavoriteCastOfMovieByUser(userId: Long, movieId: Long): Cast
    fun addFavoriteCastOfTvShow(userId: Long, showId: Long, castId: Long): Cast
    fun getFavoriteCastOfTvShowByUser(userId: Long, showId: Long): Cast
}