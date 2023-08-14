package com.sorsix.backend.service.implementations.CastServiceImpl

import com.sorsix.backend.domain.views.TopFiveCastOfMovieViewEntity
import com.sorsix.backend.domain.views.TopFiveCastOfShowViewEntity
import com.sorsix.backend.repository.view.TopFiveCastOfMovieViewRepository
import com.sorsix.backend.repository.view.TopFiveCastOfShowViewRepository
import com.sorsix.backend.service.interfaces.CastService.TopCastService
import org.springframework.stereotype.Service

@Service
class TopCastServiceImpl(
    private val topFiveCastOfMovieViewRepository: TopFiveCastOfMovieViewRepository,
    private val topFiveCastOfShowViewRepository: TopFiveCastOfShowViewRepository
) : TopCastService {
    override fun getTopFiveCastsOfMovie(movieId: Long): List<TopFiveCastOfMovieViewEntity> =
        topFiveCastOfMovieViewRepository.getTopFiveCastOfMovieViewEntitiesByMovieId(movieId)

    override fun getTopFiveCastsOfTvShow(showId: Long): List<TopFiveCastOfShowViewEntity> =
        topFiveCastOfShowViewRepository.getTopFiveCastOfShowViewEntityByShowId(showId)
}