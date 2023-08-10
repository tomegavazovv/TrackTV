package com.sorsix.backend.service

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.user.FavoriteMovieCast
import com.sorsix.backend.domain.user.FavoriteShowCast
import com.sorsix.backend.domain.views.TopFiveCastOfMovieViewEntity
import com.sorsix.backend.domain.views.TopFiveCastOfShowViewEntity

interface FavoriteCastService {
    fun addFavoriteCastOfMovie(userId: Long, movieId: Long, castId: Long): Cast?

    fun getFavoriteCastOfMovieByUser(userId: Long, movieId: Long): Cast?

    fun getTopFiveCastsOfMovie(movieId: Long): List<TopFiveCastOfMovieViewEntity>

    fun addFavoriteCastOfTvShow(userId: Long, showId: Long, castId: Long): Cast?

    fun getFavoriteCastOfTvShowByUser(userId: Long, showId: Long): Cast?

    fun getTopFiveCastsOfTvShow(showId: Long): List<TopFiveCastOfShowViewEntity>

}