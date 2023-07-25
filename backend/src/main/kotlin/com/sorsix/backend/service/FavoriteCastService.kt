package com.sorsix.backend.service

import com.sorsix.backend.domain.TopFiveCastProjection
import com.sorsix.backend.domain.user.FavoriteMovieCast
import com.sorsix.backend.domain.user.FavoriteShowCast

interface FavoriteCastService {
    fun addFavoriteCastOfMovie(userId: Long, movieId: Long, castId: Long): FavoriteMovieCast?

    fun getFavoriteCastOfMovieByUser(userId: Long, movieId: Long): FavoriteMovieCast?

    fun getTopFiveCastsOfMovie(movieId: Long): List<TopFiveCastProjection>

    fun addFavoriteCastOfTvShow(userId: Long, showId: Long, castId: Long): FavoriteShowCast?

    fun getFavoriteCastOfTvShowByUser(userId: Long, showId: Long): FavoriteShowCast?

    fun getTopFiveCastsOfTvShow(showId: Long): List<TopFiveCastProjection>

}