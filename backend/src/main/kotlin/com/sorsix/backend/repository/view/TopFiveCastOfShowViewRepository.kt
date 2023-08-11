package com.sorsix.backend.repository.view

import com.sorsix.backend.domain.views.TopFiveCastOfMovieViewEntity
import com.sorsix.backend.domain.views.TopFiveCastOfShowViewEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TopFiveCastOfShowViewRepository : JpaRepository<TopFiveCastOfShowViewEntity, Long> {

    fun getTopFiveCastOfShowViewEntityByShowId(movieId: Long): List<TopFiveCastOfShowViewEntity>


}