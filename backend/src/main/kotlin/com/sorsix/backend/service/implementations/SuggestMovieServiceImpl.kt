package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.movie.SuggestMovie
import com.sorsix.backend.exceptions.MovieNotFoundException
import com.sorsix.backend.exceptions.MovieSuggestionExists
import com.sorsix.backend.exceptions.UserNotFoundException
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.movie.MovieRepository
import com.sorsix.backend.repository.movie.SuggestMovieRepository
import com.sorsix.backend.service.interfaces.SuggestMovieService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SuggestMovieServiceImpl(
    private val suggestMovieRepository: SuggestMovieRepository,
    private val userRepository: UserRepository,
    private val movieRepository: MovieRepository
) : SuggestMovieService {
    override fun suggest(fromId: Long, toId: Long, movieId: Long): SuggestMovie {
        val suggestedFromUser: User =
            userRepository.findByIdOrNull(fromId) ?: throw UserNotFoundException("User with id $fromId not found.")
        val suggestedToUser: User =
            userRepository.findByIdOrNull(toId) ?: throw UserNotFoundException("User with id $toId not found.")
        val movie: Movie =
            movieRepository.findByIdOrNull(movieId) ?: throw MovieNotFoundException(movieId)

        if (suggestMovieRepository.existsBySuggestedFromUserIdAndSuggestedToUserIdAndMovieId(
                suggestedFromUser, suggestedToUser, movie
            )
        ) {
            throw MovieSuggestionExists("Suggestion for movie with id $movieId from user with id $fromId to user with id $toId already exists.")
        }
        return suggestMovieRepository.save(
            SuggestMovie(
                suggestedFromUserId = suggestedFromUser, suggestedToUserId = suggestedToUser, movieId = movie
            )
        )
    }

    override fun getSuggestions(userId: Long): List<SuggestMovie> {
        val user: User = userRepository.findById(userId).get()
        return suggestMovieRepository.findAllBySuggestedToUserId(user)
    }
}