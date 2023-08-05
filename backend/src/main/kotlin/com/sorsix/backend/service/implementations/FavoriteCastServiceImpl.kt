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
    override fun addFavoriteCastOfMovie(userId: Long, movieId: Long, castId: Long): FavoriteMovieCast {
        val uwm = watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()

        val cast: Cast = castRepository.findByIdOrNull(castId) ?: throw CastNotFoundException(castId)

        val favoriteMovieCast = FavoriteMovieCast(watchedMovie = uwm[0], cast = cast)
        return favoriteMovieCastRepository.save(favoriteMovieCast)
    }

    override fun getFavoriteCastOfMovieByUser(userId: Long, movieId: Long): FavoriteMovieCast {
        val uwm = watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()

        return favoriteMovieCastRepository.findByWatchedMovie(uwm[0]) ?: throw FavoriteCastNotFoundException()
    }

    override fun getTopFiveCastsOfMovie(movieId: Long): List<TopFiveCastOfMovieViewEntity> =
        topFiveCastOfMovieViewRepository.getTopFiveCastForMovie(movieId)


    override fun addFavoriteCastOfTvShow(userId: Long, showId: Long, castId: Long): FavoriteShowCast {
        val watchedShow =
            watchShowRepository.findByUserIdAndShowId(userId, showId) ?: throw WatchedShowNotFoundException()

        val cast = castRepository.findByIdOrNull(castId) ?: throw CastNotFoundException(castId)

        return favoriteShowCastRepository.save(FavoriteShowCast(userWatchShow = watchedShow, cast = cast))
    }

    override fun getFavoriteCastOfTvShowByUser(userId: Long, showId: Long): FavoriteShowCast {
        val watchedShow =
            watchShowRepository.findByUserIdAndShowId(userId, showId) ?: throw WatchedShowNotFoundException()

        return favoriteShowCastRepository.findByUserWatchShow(watchedShow) ?: throw FavoriteCastNotFoundException()
    }

    override fun getTopFiveCastsOfTvShow(showId: Long): List<TopFiveCastOfShowViewEntity> =
        topFiveCastOfShowViewRepository.getTopFiveCastForShow(showId)
}


