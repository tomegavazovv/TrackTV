package com.sorsix.backend.service

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.dto.ShowCommentDto
import com.sorsix.backend.dto.EpisodeDto
import com.sorsix.backend.dto.TvShowDto

interface TvShowService {

    fun getById(userId: Long, id: Long): TvShowDto

    fun commentShow(userId: Long, showId: Long, comment: String): List<ShowCommentDto>

    fun getComments(showId: Long): List<ShowCommentDto>

    fun getCast(showId: Long): List<Cast>

    fun getEpisodes(userId: Long, showId: Long, seasonNumber: Long): List<EpisodeDto>

}