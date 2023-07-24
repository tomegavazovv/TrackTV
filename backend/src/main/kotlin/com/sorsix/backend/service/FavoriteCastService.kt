package com.sorsix.backend.service

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.user.UserFavoriteMovieCast

interface FavoriteCastService {
    fun addFavoriteMovieCast(userId: Long, movieId: Long, castId: Long): UserFavoriteMovieCast

    fun getFavoriteMovieCastByUser(userId: Long, movieId: Long): UserFavoriteMovieCast

    fun getTopFiveCastsOfMovie(movieId: Long): Map<Cast, Double>

    fun addFavoriteTvShowCast(userId: Long, showId: Long, castId: Long): Cast

    fun getFavoriteTvShowCastByUser(userId: Long, showId: Long): Cast

    fun getTopFiveCastsOfTvShow(showId: Long): Map<Cast, Double>

}