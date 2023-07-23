package com.sorsix.backend.domain.movie

import com.sorsix.backend.domain.Cast
import jakarta.persistence.*

@Entity
@Table(name = "movie_cast")
data class MovieCast(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "movie_id")
    val movie: Movie = Movie(),

    @ManyToOne
    @JoinColumn(name = "cast_id")
    val cast: Cast = Cast()
)