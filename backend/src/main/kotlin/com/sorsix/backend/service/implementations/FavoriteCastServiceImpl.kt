package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.TopFiveCastProjection
import com.sorsix.backend.domain.user.FavoriteMovieCast
import com.sorsix.backend.domain.user.FavoriteShowCast
import com.sorsix.backend.exceptions.CastNotFoundException
import com.sorsix.backend.exceptions.FavoriteCastNotFoundException
import com.sorsix.backend.exceptions.WatchedMovieNotFoundException
import com.sorsix.backend.exceptions.WatchedShowNotFoundException
import com.sorsix.backend.repository.CastRepository
import com.sorsix.backend.repository.user.FavoriteMovieCastRepository
import com.sorsix.backend.repository.user.FavoriteShowCastRepository
import com.sorsix.backend.repository.user.WatchMovieRepository
import com.sorsix.backend.repository.user.WatchShowRepository
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
    override fun addFavoriteCastOfMovie(userId: Long, movieId: Long, castId: Long): FavoriteMovieCast {
        val uwm = watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()

        val cast: Cast = castRepository.findByIdOrNull(castId) ?: throw CastNotFoundException(castId)

        val favoriteMovieCast = FavoriteMovieCast(watchedMovie = uwm, cast = cast)
        return favoriteMovieCastRepository.save(favoriteMovieCast)
    }

    override fun getFavoriteCastOfMovieByUser(userId: Long, movieId: Long): FavoriteMovieCast {
        val uwm = watchMovieRepository.findByUserIdAndMovieId(userId, movieId) ?: throw WatchedMovieNotFoundException()

        return favoriteMovieCastRepository.findByWatchedMovie(uwm) ?: throw FavoriteCastNotFoundException()
    }

    override fun getTopFiveCastsOfMovie(movieId: Long): List<TopFiveCastProjection> =
        topFiveCastProjections(favoriteMovieCastRepository.getTopFiveCastOfMovie(movieId))


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

    override fun getTopFiveCastsOfTvShow(showId: Long): List<TopFiveCastProjection> =
        topFiveCastProjections(favoriteShowCastRepository.getTopFiveCastOfShow(showId))

    private fun topFiveCastProjections(topFiveCastsData: List<TopFiveCastProjection>): List<TopFiveCastProjection> =
        topFiveCastsData.map { castData ->
            object : TopFiveCastProjection {
                override val id: Long = castData.id
                override val role: String = castData.role
                override val name: String = castData.name
                override val imageUrl: String = castData.imageUrl
            }
        }
}


