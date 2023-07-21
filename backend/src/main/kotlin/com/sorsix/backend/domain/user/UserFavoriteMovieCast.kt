package com.sorsix.backend.domain.user

import com.sorsix.backend.domain.Cast
import jakarta.persistence.*

@Entity
@Table(name = "user_favorite_movie_cast")
data class UserFavoriteMovieCast(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_movie_id")
    val userWatchedMovie: UserWatchedMovie = UserWatchedMovie(),

    @ManyToOne
    @JoinColumn(name = "cast_id")
    val cast: Cast = Cast()
)