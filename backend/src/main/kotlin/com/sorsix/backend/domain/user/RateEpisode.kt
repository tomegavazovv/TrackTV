package com.sorsix.backend.domain.user

import jakarta.persistence.*

@Entity
@Table(name = "user_rate_episode")
data class RateEpisode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_episode_id")
    val watchEpisode: WatchEpisode,

    @Column(name = "rating", nullable = false)
    val rating: Int,
)
