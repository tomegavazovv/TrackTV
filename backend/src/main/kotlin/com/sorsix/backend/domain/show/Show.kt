package com.sorsix.backend.domain.show

import jakarta.persistence.*

@Entity
@Table(name = "show")
data class Show(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,

    @Column(name = "image_url")
    val imageUrl: String,

    @Column(name = "num_of_seasons")
    val numOfSeasons: Int,

    @Column(name = "num_of_episodes")
    val numOfEpisodes: Int,

    @Column(name = "release_year")
    val releaseYear: String = "2000",

    val popularity: Int
)