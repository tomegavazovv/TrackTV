package com.sorsix.backend.repository.user

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.domain.user.CommentShow
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShowCommentRepository : JpaRepository<CommentShow, Long> {

    fun existsByUserAndShow(user: User, show: Show): Boolean

    fun findAllByShowOrderByDateDesc(show: Show): List<CommentShow>
}