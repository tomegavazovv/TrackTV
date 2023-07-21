package com.sorsix.backend.domain.movie

import jakarta.persistence.*

@Entity
@Table(name = "movie")
data class Movie(
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val title: String = "",

    @Column(name = "image_url")
    val imageUrl: String = "",

//    ???
//    @OneToMany(mappedBy = "movie")
//    val casts: Set<MovieCast> = emptySet()
)