package com.sorsix.backend.repository.movie

import com.sorsix.backend.domain.friendship.Friend
import com.sorsix.backend.domain.movie.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<Movie, Long>