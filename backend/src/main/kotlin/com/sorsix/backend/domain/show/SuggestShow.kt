package com.sorsix.backend.domain.show

import com.sorsix.backend.domain.User
import jakarta.persistence.*

@Entity
@Table(name = "suggest_show")
data class SuggestShow (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val Id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "suggested_from_user_id")
    val suggestedFromUserId: User,

    @ManyToOne
    @JoinColumn(name = "suggested_to_user_id")
    val suggestedToUserId: User,

    @ManyToOne
    @JoinColumn(name = "show_id")
    val showId: Show

)