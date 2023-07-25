package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.TopFiveCastProjection
import com.sorsix.backend.domain.user.FavoriteMovieCast
import com.sorsix.backend.domain.user.FavoriteShowCast
import com.sorsix.backend.repository.CastRepository
import com.sorsix.backend.repository.user.FavoriteMovieCastRepository
import com.sorsix.backend.repository.user.FavoriteShowCastRepository
import com.sorsix.backend.repository.user.WatchShowRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.service.FavoriteCastService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FavoriteCastServiceImpl(
    val watchMovieRepository: WatchMovieRepository,
    val favoriteMovieCastRepository: FavoriteMovieCastRepository,
    val castRepository: CastRepository,
    val favoriteShowCastRepository: FavoriteShowCastRepository,
    val watchShowRepository: WatchShowRepository
) : FavoriteCastService {
    override fun addFavoriteCastOfMovie(userId: Long, movieId: Long, castId: Long): FavoriteMovieCast? =
        watchMovieRepository.findByUserIdAndMovieId(userId, movieId)?.let { uwm ->
            val cast: Cast? = castRepository.findByIdOrNull(castId)

            return cast?.let {
                val favoriteMovieCast = FavoriteMovieCast(watchedMovie = uwm, cast = cast)
                return favoriteMovieCastRepository.save(favoriteMovieCast)
            }
        }

    override fun getFavoriteCastOfMovieByUser(userId: Long, movieId: Long): FavoriteMovieCast? =
        watchMovieRepository.findByUserIdAndMovieId(userId, movieId)?.let {
            favoriteMovieCastRepository.findByWatchedMovie(it)
        }

    override fun getTopFiveCastsOfMovie(movieId: Long): List<TopFiveCastProjection> =
        favoriteMovieCastRepository.getTopFiveCastOfMovie(movieId).let { topFiveCastProjections(it) }


    override fun addFavoriteCastOfTvShow(userId: Long, showId: Long, castId: Long): FavoriteShowCast? =
        watchShowRepository.findByUserIdAndShowId(userId, showId)?.let { uws ->
            val cast: Cast? = castRepository.findByIdOrNull(castId)

            return cast?.let {
                val userFavoriteMovieCast = FavoriteShowCast(userWatchShow = uws, cast = cast)
                return favoriteShowCastRepository.save(userFavoriteMovieCast)
            }
        }

    override fun getFavoriteCastOfTvShowByUser(userId: Long, showId: Long): FavoriteShowCast? =
        watchShowRepository.findByUserIdAndShowId(userId, showId)?.let {
            favoriteShowCastRepository.findByUserWatchShow(it)
        }


    override fun getTopFiveCastsOfTvShow(showId: Long): List<TopFiveCastProjection> {
        val topFiveCastsData = favoriteShowCastRepository.getTopFiveCastOfShow(showId)
        return topFiveCastProjections(topFiveCastsData)
    }

    private fun topFiveCastProjections(topFiveCastsData: List<TopFiveCastProjection>): MutableList<TopFiveCastProjection> {
        val topFiveCasts: MutableList<TopFiveCastProjection> = mutableListOf()

        for (castData in topFiveCastsData) {
            val castId = castData.id
            val role = castData.role
            val name = castData.name
            val imageUrl = castData.imageUrl

            topFiveCasts.add(object : TopFiveCastProjection {
                override val id: Long = castId
                override val role: String = role
                override val name: String = name
                override val imageUrl: String = imageUrl
            })
        }

        return topFiveCasts
    }
}


