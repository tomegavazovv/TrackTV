package com.sorsix.backend.domain.friendship

import com.sorsix.backend.domain.User
import jakarta.persistence.*

@Entity
@Table(name = "friends")
data class Friend(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User = User(),

    @ManyToOne
    @JoinColumn(name = "friend_id")
    val friend: User = User()
)
