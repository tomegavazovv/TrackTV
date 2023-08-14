package com.sorsix.backend.service.interfaces.RatingService

interface MovieRatingService {

    fun rateMovie(userId: Long, movieId: Long, rating: Int)

    fun getAverageMovieRating(movieId: Long): Double


}