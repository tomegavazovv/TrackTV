package com.sorsix.backend.domain.show

import jakarta.persistence.*

@Entity
@Table(name = "show")
data class Show(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String = "",

    @Column(name = "image_url")
    val imageUrl: String = "",

    @Column(name = "num_of_seasons")
    val numOfSeasons: Int = 0,

    @Column(name = "num_of_episodes")
    val numOfEpisodes: Int = 0
)