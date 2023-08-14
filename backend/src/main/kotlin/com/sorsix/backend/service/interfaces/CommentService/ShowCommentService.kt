package com.sorsix.backend.service.interfaces.CommentService

import com.sorsix.backend.dto.ShowCommentDto

interface ShowCommentService {
    fun commentShow(userId: Long, showId: Long, comment: String): List<ShowCommentDto>
    fun getShowComments(showId: Long): List<ShowCommentDto>
}