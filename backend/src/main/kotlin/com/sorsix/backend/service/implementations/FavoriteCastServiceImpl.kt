package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.user.UserFavoriteMovieCast
import com.sorsix.backend.service.FavoriteCastService

class FavoriteCastServiceImpl: FavoriteCastService{
    override fun addFavoriteMovieCast(userId: Long, movieId: Long, castId: Long): UserFavoriteMovieCast {
        TODO("Not yet implemented")
    }

    override fun getFavoriteMovieCastByUser(userId: Long, movieId: Long): UserFavoriteMovieCast {
        TODO("Not yet implemented")
    }

    override fun getTopFiveCastsOfMovie(movieId: Long): Map<Cast, Double> {
        TODO("Not yet implemented")
    }

    override fun addFavoriteTvShowCast(userId: Long, showId: Long, castId: Long): Cast {
        TODO("Not yet implemented")
    }

    override fun getFavoriteTvShowCastByUser(userId: Long, showId: Long): Cast {
        TODO("Not yet implemented")
    }

    override fun getTopFiveCastsOfTvShow(showId: Long): Map<Cast, Double> {
        TODO("Not yet implemented")
    }

}