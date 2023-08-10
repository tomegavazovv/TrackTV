package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.user.FavoriteMovieCast
import com.sorsix.backend.domain.user.FavoriteShowCast
import com.sorsix.backend.domain.views.TopFiveCastOfMovieViewEntity
import com.sorsix.backend.domain.views.TopFiveCastOfShowViewEntity
import com.sorsix.backend.exceptions.CastNotFoundException
import com.sorsix.backend.exceptions.FavoriteCastNotFoundException
import com.sorsix.backend.exceptions.WatchedMovieNotFoundException
import com.sorsix.backend.exceptions.WatchedShowNotFoundException
import com.sorsix.backend.repository.CastRepository
import com.sorsix.backend.repository.user.FavoriteMovieCastRepository
import com.sorsix.backend.repository.user.FavoriteShowCastRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.repository.user.WatchShowRepository
import com.sorsix.backend.repository.view.TopFiveCastOfMovieViewRepository
import com.sorsix.backend.repository.view.TopFiveCastOfShowViewRepository
import com.sorsix.backend.service.FavoriteCastService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FavoriteCastServiceImpl(
    val watchMovieRepository: WatchMovieRepository,
    val favoriteMovieCastRepository: FavoriteMovieCastRepository,
    val castRepository: CastRepository,
    val favoriteShowCastRepository: FavoriteShowCastRepository,
    val watchShowRepository: WatchShowRepository,
    val topFiveCastOfMovieViewRepository: TopFiveCastOfMovieViewRepository,
    val topFiveCastOfShowViewRepository: TopFiveCastOfShowViewRepository
) : FavoriteCastService {
    override fun addFavoriteCastOfMovie(userId: Long, movieId: Long, castId: Long): Cast? {
        val uwm = watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()
        val cast: Cast = castRepository.findByIdOrNull(castId) ?: throw CastNotFoundException(castId)

        val favMovieCast = favoriteMovieCastRepository.findByWatchedMovieUserIdAndWatchedMovieMovieId(userId, movieId)?.let {
            FavoriteMovieCast(it.id, uwm, cast)
        } ?: FavoriteMovieCast(watchedMovie = uwm, cast = cast)

        return favoriteMovieCastRepository.save(favMovieCast).cast
    }

    override fun getFavoriteCastOfMovieByUser(userId: Long, movieId: Long): Cast? {
        val uwm = watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()
        return favoriteMovieCastRepository.findByWatchedMovie(uwm)?.cast ?: throw FavoriteCastNotFoundException()
    }

    override fun getTopFiveCastsOfMovie(movieId: Long): List<TopFiveCastOfMovieViewEntity> =
        topFiveCastOfMovieViewRepository.getTopFiveCastOfMovieViewEntitiesByMovieId(movieId)

    override fun addFavoriteCastOfTvShow(userId: Long, showId: Long, castId: Long): Cast {
        val watchedShow =
            watchShowRepository.findByUserIdAndShowId(userId, showId) ?: throw WatchedShowNotFoundException()
        val cast = castRepository.findByIdOrNull(castId) ?: throw CastNotFoundException(castId)

        val favShowCast = favoriteShowCastRepository.findByUserWatchShow_UserIdAndUserWatchShow_ShowId(userId, showId)?.let {
            FavoriteShowCast(it.id, watchedShow, cast)
        } ?: FavoriteShowCast(userWatchShow = watchedShow, cast = cast)

        return favoriteShowCastRepository.save(favShowCast).cast

    }

    override fun getFavoriteCastOfTvShowByUser(userId: Long, showId: Long): Cast? {
        val watchedShow =
            watchShowRepository.findByUserIdAndShowId(userId, showId) ?: throw WatchedShowNotFoundException()

        return favoriteShowCastRepository.findByUserWatchShow(watchedShow)?.cast ?: throw FavoriteCastNotFoundException()
    }

    override fun getTopFiveCastsOfTvShow(showId: Long): List<TopFiveCastOfShowViewEntity> =
        topFiveCastOfShowViewRepository.getTopFiveCastForShow(showId)
}


