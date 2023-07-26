package com.sorsix.backend.domain.user

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.movie.Movie
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "user_watched_movie")
data class WatchedMovie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "movie_id")
    val movie: Movie ,

    @Column(name = "date")
    val date: LocalDate = LocalDate.now()
)