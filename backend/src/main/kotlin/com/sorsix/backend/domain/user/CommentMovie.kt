package com.sorsix.backend.domain.user

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.show.Show
import jakarta.persistence.*

@Entity
@Table(name = "movie_comment")
data class CommentMovie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "show_id")
    val movie: Movie,

    val comment: String
)