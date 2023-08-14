package com.sorsix.backend.service.implementations.CommentServiceImpl

import com.sorsix.backend.domain.user.CommentShow
import com.sorsix.backend.dto.ShowCommentDto
import com.sorsix.backend.exceptions.ShowNotFoundException
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.repository.user.ShowCommentRepository
import com.sorsix.backend.service.interfaces.CommentService.ShowCommentService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ShowCommentServiceImpl(
    private val userRepository: UserRepository,
    private val showRepository: ShowRepository,
    private val commentShowRepository: ShowCommentRepository
) : ShowCommentService {
    override fun commentShow(userId: Long, showId: Long, comment: String): List<ShowCommentDto> {
        val user = userRepository.findById(userId).orElse(null)
        val show = showRepository.findByIdOrNull(showId) ?: throw ShowNotFoundException(showId)

        commentShowRepository.save(CommentShow(user = user, show = show, comment = comment, date = LocalDateTime.now()))
            .let {
                ShowCommentDto(user = it.user.email, comment = it.comment, date = it.date)
            }

        return commentShowRepository.findAllByShowOrderByDateDesc(show)
            .map { ShowCommentDto(user.email, it.comment, it.date) }
    }

    override fun getShowComments(showId: Long): List<ShowCommentDto> {
        val show = showRepository.findByIdOrNull(showId) ?: throw ShowNotFoundException(showId)

        return commentShowRepository.findAllByShowOrderByDateDesc(show)
            .map { ShowCommentDto(it.user.email, it.comment, it.date) }
    }
}