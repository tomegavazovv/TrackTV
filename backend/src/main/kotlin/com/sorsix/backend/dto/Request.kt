package com.sorsix.backend.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

data class LoginDto(
    @get: Email(message = "The entered email is not a valid email.")
    val email: String,

    @get: Size(min=6, message = "Password length must be bigger than 6 characters.")
    val password: String,
)

data class RegisterDto(
    @get: Size(min=6, max = 30, message = "Username length must be between 6 and 30 characters.")
    val username: String,

    @get: Size(min=6, message = "Password length must be bigger than 6 characters.")
    val password: String,

    @get: Email(message = "The entered email is not a valid email.")
    val email: String
)

data class AddFavoriteCastDto(
    val id: Long,
    val castId: Long
)

data class CommentMovieBody(
    val movieId: Long,

    @get: Max(10, message = "Rating value must be between 1 and 10.")
    val rating: Int,

    @get: Size(max=400, message = "Comment length must be less than 400 characters.")
    val comment: String
)

data class CommentShowDto(
    val showId: Long,
    val comment: String
)


data class RateEpisodeDto(
    val episodeId: Long,

    @get: Max(10, message = "Rating value must be between 1 and 10.")
    @get: Min(1, message = "Rating value must be between 1 and 10.")
    val rating: Int,
)

data class RateMovieDto(
    val movieId: Long,
    val rating: Number
)


