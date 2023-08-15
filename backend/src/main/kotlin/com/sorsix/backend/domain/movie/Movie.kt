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

    val popularity: Int = 0,

    @Column(name = "release_year")
    val releaseYear: String = "1900",

    @Column(name = "duration")
    val duration: String = "2h 55m"

//    ???
//    @OneToMany(mappedBy = "movie")
//    val casts: Set<MovieCast> = emptySet()
)