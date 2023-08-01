package com.sorsix.backend.domain

interface TopFiveCastProjection {
    val id: Long
    val role: String
    val name: String
    val imageUrl: String
}