package com.sorsix.backend.domain.user

import com.sorsix.backend.domain.Cast
import jakarta.persistence.*

@Entity
@Table(name = "user_favorite_movie_cast")
data class FavoriteMovieCast(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_movie_id")
    val watchedMovie: WatchedMovie = WatchedMovie(),

    @ManyToOne
    @JoinColumn(name = "cast_id")
    val cast: Cast = Cast()

)