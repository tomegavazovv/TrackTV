package com.sorsix.backend.service.implementations.CastServiceImpl

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.views.TopFiveCastOfMovieViewEntity
import com.sorsix.backend.domain.views.TopFiveCastOfShowViewEntity
import com.sorsix.backend.service.interfaces.CastService.CastRetrievalService
import com.sorsix.backend.service.interfaces.CastService.CastService
import com.sorsix.backend.service.interfaces.CastService.FavoriteCastService
import com.sorsix.backend.service.interfaces.CastService.TopCastService
import org.springframework.stereotype.Service

@Service
class CastServiceImpl(
    private val favoriteCastService: FavoriteCastService,
    private val castRetrievalService: CastRetrievalService,
    private val topCastService: TopCastService
) : CastService {
    override fun addFavoriteCastOfMovie(userId: Long, movieId: Long, castId: Long): Cast {
        return favoriteCastService.addFavoriteCastOfMovie(userId, movieId, castId)
    }

    override fun getFavoriteCastOfMovieByUser(userId: Long, movieId: Long): Cast {
        return favoriteCastService.getFavoriteCastOfMovieByUser(userId, movieId)
    }

    override fun getTopFiveCastsOfMovie(movieId: Long): List<TopFiveCastOfMovieViewEntity> {
        return topCastService.getTopFiveCastsOfMovie(movieId)
    }

    override fun addFavoriteCastOfTvShow(userId: Long, showId: Long, castId: Long): Cast {
        return favoriteCastService.addFavoriteCastOfTvShow(userId, showId, castId)
    }

    override fun getMovieCast(movieId: Long): List<Cast> {
        return castRetrievalService.getMovieCast(movieId)
    }

    override fun getShowCast(showId: Long): List<Cast> {
        return castRetrievalService.getShowCast(showId)
    }

    override fun getFavoriteCastOfTvShowByUser(userId: Long, showId: Long): Cast {
        return favoriteCastService.getFavoriteCastOfTvShowByUser(userId, showId)
    }

    override fun getTopFiveCastsOfTvShow(showId: Long): List<TopFiveCastOfShowViewEntity> {
        return topCastService.getTopFiveCastsOfTvShow(showId)
    }
}


