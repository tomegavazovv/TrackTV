package com.sorsix.backend.repository.view

import com.sorsix.backend.domain.views.TopFiveCastOfShowViewEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TopFiveCastOfShowViewRepository : JpaRepository<TopFiveCastOfShowViewEntity, Long> {
    @Query(
        "SELECT c FROM top_five_cast_of_show_view " +
                "JOIN user_watch_show AS uws ON c.id = uwm.cast_id " +
                "WHERE uws.show_id = :showId " +
                "ORDER BY c.show_count DESC"
        , nativeQuery = true)
    fun getTopFiveCastForShow(@Param("showId") showId: Long): List<TopFiveCastOfShowViewEntity>
}