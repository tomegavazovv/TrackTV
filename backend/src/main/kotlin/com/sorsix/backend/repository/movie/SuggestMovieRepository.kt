package com.sorsix.backend.repository.movie

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.movie.SuggestMovie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
interface SuggestMovieRepository : JpaRepository<SuggestMovie, Long> {


    fun existsBySuggestedFromUserIdAndSuggestedToUserIdAndMovieId(from: User, to: User, movie: Movie): Boolean

    fun findAllBySuggestedToUserId(user: User): List<SuggestMovie>
}