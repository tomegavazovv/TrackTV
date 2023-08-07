package com.sorsix.backend.domain.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Immutable

@Entity
@Table(name = "top_five_cast_of_movie_view")
@Immutable
class TopFiveCastOfMovieViewEntity(
    @Id
    val id: Long,

    @Column(name = "movie_id")
    val movieId: Long,

    @Column
    val role: String,

    @Column
    val name: String,

    @Column(name = "image_url")
    val imageUrl: String,

    @Column(name = "movie_count")
    val movieCount: Long
)