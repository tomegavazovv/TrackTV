package com.sorsix.backend.domain.user

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_comment_movie")
data class CommentMovie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "watched_movie_id")
    val watchMovie: WatchMovie,

    val date: LocalDateTime = LocalDateTime.now(),

    var comment: String = ""
)