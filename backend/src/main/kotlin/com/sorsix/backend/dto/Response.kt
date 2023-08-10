package com.sorsix.backend.dto

import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.show.Episode

data class LoginResponseDto(
    val token: String,
)

data class ShowCommentDto(
    val user: String,
    val comment: String,
)

data class EpisodeDto(
    val episode: Episode,
    var watched: Boolean
)

data class MovieRatingDto(
    val rating: Number,
    val comment: String?,
    val user: String
)

data class MovieDto(
    val data: Movie,
    val watched: Boolean
    )