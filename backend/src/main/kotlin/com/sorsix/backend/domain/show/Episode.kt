package com.sorsix.backend.domain.show

import jakarta.persistence.*

@Entity
@Table(name = "episode")
data class Episode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "season_number")
    val season: Season = Season(),

    @ManyToOne
    @JoinColumn(name = "show_id")
    val show: Show = Show(),

    val num: Int = 0,

    val title: String = ""
)