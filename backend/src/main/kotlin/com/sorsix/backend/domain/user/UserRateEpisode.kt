package com.sorsix.backend.domain.user

import jakarta.persistence.*

@Entity
@Table(name = "user_rate_episode")
data class UserRateEpisode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_episode_id")
    val userEpisode: UserWatchedEpisode = UserWatchedEpisode(),

    @Column(name = "rating", nullable = false)
    val rating: Int = 0,

    val comment: String? = ""
)
