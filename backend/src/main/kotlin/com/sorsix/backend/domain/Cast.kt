package com.sorsix.backend.domain

import com.sorsix.backend.domain.movie.MovieCast
import jakarta.persistence.*

@Entity
@Table(name = "tracktv_cast")
data class Cast(
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val role: String = "",

    val name: String = "",

    @Column(name = "image_url")
    val imageUrl: String = "",

//    ???
//    @OneToMany(mappedBy = "cast")
//    val movies: Set<MovieCast> = emptySet()
)