package com.sorsix.backend.dto

data class LoginDto(
    val email: String,
    val password: String,
)

data class RegisterDto(
    val username: String, val password: String, val email: String
)

data class AddFavoriteMovieCastDto(
    val movieId: Long,
    val castId: Long
)

data class RateMovieDto(
    val movieId: Long,
    val rating: Int,
    val comment: String?
)


