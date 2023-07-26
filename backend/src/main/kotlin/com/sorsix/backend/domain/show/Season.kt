package com.sorsix.backend.domain.show

import jakarta.persistence.*

@Entity
@Table(name = "season")
data class Season(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val number: Long = 0,
)