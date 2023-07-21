package com.sorsix.backend.domain

import jakarta.persistence.*

@Entity
@Table(name = "tracktv_user")
data class User(
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "username")
    val name: String = "",

    val email: String = "",

    val password: String = ""
)