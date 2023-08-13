package com.sorsix.backend.dto

import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.show.Episode
import com.sorsix.backend.domain.show.Show
import java.time.LocalDateTime

data class LoginResponseDto(
    val token: String,
)

data class ShowCommentDto(
    val user: String,
    val comment: String,
    val date: LocalDateTime
)

data class EpisodeDto(
    val episode: Episode,
    var watched: Boolean,
    val rating: Number,
    val averageRating: Number
)

data class MovieCommentDto(
    val comment: String,
    val user: String,
    val date: LocalDateTime
)

data class MovieDto(
    val data: Movie,
    val watched: Boolean,
    val description: String,
    val rating: Number
)

data class TvShowDto(
    val data: Show,
    val watched: Boolean
)



data class AverageEpisodeRatingDto(
    val averageRating: Number
)
