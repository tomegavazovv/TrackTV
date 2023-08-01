package com.sorsix.backend.repository.show

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.domain.show.SuggestShow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
interface SuggestShowRepository: JpaRepository<SuggestShow, Long> {

    fun findAllBySuggestedToUserId(user: User): List<SuggestShow>

    fun existsBySuggestedFromUserIdAndSuggestedToUserIdAndShowId(from: User, to: User, show: Show): Boolean

}