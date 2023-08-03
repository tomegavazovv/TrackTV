package com.sorsix.backend.domain.movie

import com.sorsix.backend.domain.User
import jakarta.persistence.*

@Entity
@Table(name = "suggest_movie")
data class SuggestMovie (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "suggested_from_user_id")
    val suggestedFromUserId: User,

    @ManyToOne
    @JoinColumn(name = "suggested_to_user_id")
    val suggestedToUserId: User,

    @ManyToOne
    @JoinColumn(name = "movie_id")
    val movieId: Movie

)