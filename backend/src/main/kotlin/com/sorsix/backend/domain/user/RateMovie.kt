package com.sorsix.backend.domain.user

import jakarta.persistence.*

@Entity
@Table(name = "user_rate_movie")
data class RateMovie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "watched_movie_id")
    val watchedMovie: WatchedMovie = WatchedMovie(),

    @Column(name = "rating", nullable = false)
    val rating: Int = 0,

    val comment: String? = ""
)