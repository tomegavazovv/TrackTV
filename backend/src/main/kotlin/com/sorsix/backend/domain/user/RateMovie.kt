package com.sorsix.backend.domain.user

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "user_rate_movie")
data class RateMovie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "watched_movie_id")
    val watchedMovie: WatchedMovie,

    @Column(name = "rating", nullable = false)
    var rating: Int,

    val date: LocalDateTime = LocalDateTime.now(),

    var comment: String? = ""
)