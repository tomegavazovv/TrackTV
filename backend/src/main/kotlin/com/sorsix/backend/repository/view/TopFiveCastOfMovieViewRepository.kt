package com.sorsix.backend.repository.view

import com.sorsix.backend.domain.views.TopFiveCastOfMovieViewEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TopFiveCastOfMovieViewRepository : JpaRepository<TopFiveCastOfMovieViewEntity, Long> {


    fun getTopFiveCastOfMovieViewEntitiesByMovieId(movieId: Long): List<TopFiveCastOfMovieViewEntity>
}