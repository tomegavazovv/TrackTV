package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.domain.show.SuggestShow
import com.sorsix.backend.exceptions.*
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.repository.show.SuggestShowRepository
import com.sorsix.backend.service.interfaces.SuggestShowService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SuggestShowServiceImpl(
    private val suggestShowRepository: SuggestShowRepository,
    private val userRepository: UserRepository,
    private val showRepository: ShowRepository
) : SuggestShowService {
    override fun suggest(fromId: Long, toId: Long, showId: Long): SuggestShow {
        val suggestedFromUser: User =
            userRepository.findByIdOrNull(fromId) ?: throw UserNotFoundException(fromId)
        val suggestedToUser: User =
            userRepository.findByIdOrNull(toId) ?: throw UserNotFoundException(toId)
        val show: Show =
            showRepository.findByIdOrNull(showId) ?: throw ShowNotFoundException(showId )

        if (suggestShowRepository.existsBySuggestedFromUserIdAndSuggestedToUserIdAndShowId(
                suggestedFromUser, suggestedToUser, show
            )
        ) {
            throw ShowSuggestionExists(showId, fromId, toId)
        }
        return suggestShowRepository.save(
            SuggestShow(
                suggestedFromUserId = suggestedFromUser, suggestedToUserId = suggestedToUser, showId = show
            )
        )
    }

    override fun getSuggestions(userId: Long): List<SuggestShow> {
        val user: User = userRepository.findById(userId).get()
        return suggestShowRepository.findAllBySuggestedToUserId(user)
    }
}