package com.sorsix.backend.domain.show

import com.sorsix.backend.domain.Cast
import jakarta.persistence.*

@Entity
@Table(name = "show_cast")
data class ShowCast(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "show_id")
    val show: Show = Show(),

    @ManyToOne
    @JoinColumn(name = "cast_id")
    val cast: Cast = Cast()
)