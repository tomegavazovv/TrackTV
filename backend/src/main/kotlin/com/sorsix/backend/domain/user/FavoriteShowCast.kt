package com.sorsix.backend.domain.user

import com.sorsix.backend.domain.Cast
import jakarta.persistence.*

@Entity
@Table(name = "user_favorite_show_cast")
data class FavoriteShowCast(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long =0 ,

    @ManyToOne
    @JoinColumn(name = "user_show_id")
    val userWatchShow: UserWatchShow ,

    @ManyToOne
    @JoinColumn(name = "cast_id")
    val cast: Cast
)
