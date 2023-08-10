package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.domain.user.CommentShow
import com.sorsix.backend.dto.ShowCommentDto
import com.sorsix.backend.dto.EpisodeDto
import com.sorsix.backend.exceptions.SeasonNotFoundException
import com.sorsix.backend.exceptions.ShowNotFoundException
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.show.EpisodeRepository
import com.sorsix.backend.repository.show.SeasonRepository
import com.sorsix.backend.repository.show.ShowCastRepository
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.repository.user.ShowCommentRepository
import com.sorsix.backend.repository.user.WatchEpisodeRepository
import com.sorsix.backend.service.TvShowService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TvShowServiceImpl(
    private val showRepository: ShowRepository,
    private val commentRepository: ShowCommentRepository,
    private val userRepository: UserRepository,
    private val showCastRepository: ShowCastRepository,
    private val episodeRepository: EpisodeRepository,
    private val seasonRepository: SeasonRepository,
    private val watchEpisodeRepository: WatchEpisodeRepository
) : TvShowService {
    override fun getById(id: Long): Show = this.showRepository.findByIdOrNull(id) ?: throw ShowNotFoundException(id)
    override fun commentShow(userId: Long, showId: Long, comment: String): List<ShowCommentDto> {
        val user = userRepository.findById(userId).orElse(null)
        val show = showRepository.findByIdOrNull(showId) ?: throw ShowNotFoundException(showId)

        commentRepository.save(CommentShow(user = user, show = show, comment = comment, date = LocalDateTime.now())).let {
            ShowCommentDto(user = it.user.email, comment = it.comment)
        }

        return commentRepository.findAllByShowOrderByDateDesc(show).map { ShowCommentDto(user.email, it.comment) }
    }

    override fun getComments(showId: Long): List<ShowCommentDto> {
        val show = showRepository.findByIdOrNull(showId) ?: throw ShowNotFoundException(showId)

        return commentRepository.findAllByShowOrderByDateDesc(show).map { ShowCommentDto(it.user.email, it.comment) }
    }

    override fun getCast(showId: Long): List<Cast> {
        val show = showRepository.findByIdOrNull(showId)
            ?: throw ShowNotFoundException(showId)

        return showCastRepository.findByShow(show)
            .map { it.cast }
    }

    override fun getEpisodes(userId: Long, showId: Long, seasonNumber: Long): List<EpisodeDto> {
        val show = showRepository.findByIdOrNull(showId)
            ?: throw ShowNotFoundException(showId)

        val season = seasonRepository.findByIdOrNull(seasonNumber)
            ?: throw SeasonNotFoundException(seasonNumber)

        val watchedEpisodes = watchEpisodeRepository.findAllByEpisode_ShowIdAndUserId(showId, userId)

        return episodeRepository.findAllByShowAndSeason(show, season).map {ep ->
            watchedEpisodes.find { watchedEp -> watchedEp.episode.id == ep.id }?.let {EpisodeDto(ep, watched = true)}
                ?: EpisodeDto(ep, false)

        }
    }

}