package com.sorsix.backend.domain.user

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.show.Episode
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "user_watched_episode")
data class UserWatchedEpisode(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User = User(),

    @ManyToOne
    @JoinColumn(name = "episode_id")
    val episode: Episode = Episode(),

    @Column(name = "date")
    val date: LocalDate = LocalDate.now()
)
