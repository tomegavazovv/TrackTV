package com.sorsix.backend.repository.movie

import com.sorsix.backend.domain.movie.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<Movie, Long>{

    @Query("select * from movie where popularity between 1 and 100 ORDER BY popularity limit 10",nativeQuery = true)
    fun findMostPopular(): List<Movie>

    fun findByTitle(title: String) : Movie


    fun searchByTitleContainingIgnoreCase(title: String): List<Movie>
}