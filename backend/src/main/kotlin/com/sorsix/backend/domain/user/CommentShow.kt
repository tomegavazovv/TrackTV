package com.sorsix.backend.domain.user

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.show.Show
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "show_comment")
data class CommentShow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "show_id")
    val show: Show,

    val date: LocalDateTime,

    val comment: String
)

