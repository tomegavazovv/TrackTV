package com.sorsix.backend.service.interfaces

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.dto.EpisodeDto
import com.sorsix.backend.dto.TvShowDto

interface TvShowService {

    fun getById(userId: Long, id: Long): TvShowDto

    fun getEpisodes(userId: Long, showId: Long, seasonNumber: Long): List<EpisodeDto>

    fun getMostPopularTvShows(): List<Show>

    fun searchByTitle(title: String): List<Show>

}