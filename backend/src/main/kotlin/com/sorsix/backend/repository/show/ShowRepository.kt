package com.sorsix.backend.repository.show

import com.sorsix.backend.domain.show.Show
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ShowRepository : JpaRepository<Show, Long>{
    @Query("select * from show where popularity between 1 and 100 ORDER BY popularity limit 10",nativeQuery = true)
    fun findMostPopular(): List<Show>
}