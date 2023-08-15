package com.sorsix.backend.api

import com.sorsix.backend.authentication.CustomPrincipal
import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.show.Episode
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.domain.user.WatchEpisode
import com.sorsix.backend.domain.views.TopFiveCastOfShowViewEntity
import com.sorsix.backend.dto.*
import com.sorsix.backend.service.*
import com.sorsix.backend.service.interfaces.CastService.CastService
import com.sorsix.backend.service.interfaces.CommentService.CommentService
import com.sorsix.backend.service.interfaces.RatingService.RatingService
import com.sorsix.backend.service.interfaces.TvShowService
import com.sorsix.backend.service.interfaces.WatchService.WatchService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tvshows")
class TvShowController(
    private val castService: CastService,
    private val ratingService: RatingService,
    private val watchService: WatchService,
    private val tvShowService: TvShowService,
    private val commentService: CommentService
) {

    @GetMapping("/{showId}")
    fun getShowById(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable showId: Long
    ): ResponseEntity<TvShowDto> {
        return ResponseEntity.ok(tvShowService.getById(principal.userId, showId))
    }

    @GetMapping("/search")
    fun searchByTitle(@RequestParam("title") title: String): ResponseEntity<List<Show>> {
        return ResponseEntity.ok(tvShowService.searchByTitle(title))
    }

    @GetMapping("/cast/{showId}")
    fun getCastOfShow(@PathVariable showId: Long): ResponseEntity<*> {
        return ResponseEntity.ok(castService.getShowCast(showId))
    }

    @GetMapping("/favoriteCast/{tvshowId}")
    fun getFavoriteCastByUser(
        @AuthenticationPrincipal principal: CustomPrincipal, @PathVariable tvshowId: Long
    ): ResponseEntity<Cast> {
        return ResponseEntity.ok(castService.getFavoriteCastOfTvShowByUser(principal.userId, tvshowId))
    }

    @GetMapping("/topCast/{tvshowId}")
    fun getTopCast(@PathVariable tvshowId: Long): ResponseEntity<List<TopFiveCastOfShowViewEntity>> {
        return ResponseEntity.ok(castService.getTopFiveCastsOfTvShow(tvshowId))
    }

    @PostMapping("/favoriteCast")
    fun addFavoriteCastOfTvShow(
        @RequestBody body: AddFavoriteCastDto, @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<Cast> {
        return ResponseEntity.ok(
            castService.addFavoriteCastOfTvShow(
                principal.userId, body.id, body.castId
            )
        )
    }

    @GetMapping("/ratingByUser/{episodeId}")
    fun getRatingOfEpisode(
        @AuthenticationPrincipal principal: CustomPrincipal, @PathVariable episodeId: Long
    ): ResponseEntity<RateEpisode> {
        return ResponseEntity.ok(ratingService.getRatingOfTvShowEpisodeByUser(principal.userId, episodeId))
    }

    @GetMapping("/ratings/{episodeId}")
    fun getRatingsOfTvShowEpisode(@PathVariable episodeId: Long): ResponseEntity<List<RateEpisode>> {
        return ResponseEntity.ok(ratingService.getRatingOfTvShowEpisode(episodeId))
    }

    @PostMapping("/rate")
    fun rateTvShow(
        @Validated @RequestBody body: RateEpisodeDto, @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<AverageEpisodeRatingDto> {
        return ResponseEntity.ok(
            ratingService.rateEpisodeOfTvShow(
                principal.userId,
                body.episodeId,
                body.rating,
            )
        )
    }

    @GetMapping("/comments/{showId}")
    fun getCommentsForShow(@PathVariable showId: Long): ResponseEntity<*> {
        return ResponseEntity.ok(commentService.getShowComments(showId))
    }

    @PostMapping("/comments")
    fun commentShow(
        @AuthenticationPrincipal principal: CustomPrincipal, @RequestBody body: CommentShowDto
    ): ResponseEntity<*> {
        return ResponseEntity.ok(commentService.commentShow(principal.userId, body.showId, body.comment))
    }

    @GetMapping("/watched")
    fun getWatchedMovies(@AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<List<WatchedShowDto>> {
        return ResponseEntity.ok(watchService.getWatchedTvShows(principal.userId))
    }

    @GetMapping("/episodes/{showId}/{seasonNumber}")
    fun getEpisodesOfShow(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable showId: Long,
        @PathVariable seasonNumber: Long
    ): ResponseEntity<*> {
        return ResponseEntity.ok(tvShowService.getEpisodes(principal.userId, showId, seasonNumber))
    }

    @GetMapping("/{showId}/watchedEpisodes")
    fun getWatchedEpisodesOfShow(
        @AuthenticationPrincipal principal: CustomPrincipal, @PathVariable showId: Long
    ): ResponseEntity<List<Episode>> {
        return ResponseEntity.ok(watchService.getWatchedEpisodesOfShow(principal.userId, showId))
    }


    @PostMapping("/watch/{showId}")
    fun addToWatchedShows(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable showId: Long
    ): ResponseEntity<TvShowDto> {
        return ResponseEntity.ok(watchService.addWatchedTvShow(principal.userId, showId))
    }

    @PostMapping("/watched/{episodeId}")
    fun addWatchedEpisode(
        @AuthenticationPrincipal principal: CustomPrincipal, @PathVariable episodeId: Long
    ): ResponseEntity<WatchEpisode> {
        return ResponseEntity.ok(watchService.addWatchedEpisode(principal.userId, episodeId))
    }

    @PostMapping("/unwatchEpisode/{episodeId}")
    fun unwatchEpisode(@AuthenticationPrincipal principal: CustomPrincipal, @PathVariable episodeId: Long) {
        watchService.unwatchEpisode(principal.userId, episodeId)
    }

    @PostMapping("/unwatch/{showId}")
    fun unwatchShow(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable showId: Long
    ): ResponseEntity<Unit> {
        watchService.unwatchTvShow(principal.userId, showId)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/mostPopular")
    fun getMostPopular(): ResponseEntity<*> {
        return ResponseEntity.ok(tvShowService.getMostPopularTvShows())
    }

}