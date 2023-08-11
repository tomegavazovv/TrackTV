package com.sorsix.backend.api

import com.sorsix.backend.authentication.CustomPrincipal
import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.show.Episode
import com.sorsix.backend.domain.show.Show
import com.sorsix.backend.domain.user.RateEpisode
import com.sorsix.backend.domain.user.WatchedEpisode
import com.sorsix.backend.domain.views.TopFiveCastOfShowViewEntity
import com.sorsix.backend.dto.AddFavoriteCastDto
import com.sorsix.backend.dto.CommentShowDto
import com.sorsix.backend.dto.RateEpisodeDto
import com.sorsix.backend.service.*
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tvshows")
class TvShowController(
    private val favoriteCastService: FavoriteCastService,
    private val ratingService: RatingService,
    private val watchService: WatchService,
    private val popularityService: PopularityService,
    private val tvshowService: TvShowService,
    ) {

    @GetMapping("/{showId}")
    fun getShow(@PathVariable showId: Long): ResponseEntity<Show> {
        return ResponseEntity.ok(tvshowService.getById(showId))
    }

    @PostMapping("/favoriteCast")
    fun addFavoriteCastOfTvShow(
        @RequestBody body: AddFavoriteCastDto, @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<Cast> {
        return ResponseEntity.ok(
            favoriteCastService.addFavoriteCastOfTvShow(
                principal.userId, body.id, body.castId
            )
        )
    }

    @GetMapping("/favoriteCast/{tvshowId}")
    fun getFavoriteCastOfTvShowByUser(
        @AuthenticationPrincipal principal: CustomPrincipal, @PathVariable tvshowId: Long
    ): ResponseEntity<Cast> {
        return ResponseEntity.ok(favoriteCastService.getFavoriteCastOfTvShowByUser(principal.userId, tvshowId))
    }

    @GetMapping("/topCast/{tvshowId}")
    fun getTopCast(@PathVariable tvshowId: Long): ResponseEntity<List<TopFiveCastOfShowViewEntity>> {
        return ResponseEntity.ok(favoriteCastService.getTopFiveCastsOfTvShow(tvshowId))
    }

    @PostMapping("/rate")
    fun rateTvShow(
        @Validated @RequestBody body: RateEpisodeDto, @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<RateEpisode> {
        return ResponseEntity.ok(
            ratingService.rateEpisodeOfTvShow(
                principal.userId,
                body.episodeId,
                body.rating,
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

    @GetMapping("/{showId}/watchedEpisodes")
    fun getWatchedEpisodesOfShow(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable showId: Long
    ): ResponseEntity<List<Episode>> {
        return ResponseEntity.ok(watchService.getWatchedEpisodesOfShow(principal.userId, showId))
    }

    @PostMapping("/watched/{episodeId}")
    fun addWatchedEpisode(
        @AuthenticationPrincipal principal: CustomPrincipal, @PathVariable episodeId: Long
    ): ResponseEntity<WatchedEpisode> {
        return ResponseEntity.ok(watchService.addWatchedEpisode(principal.userId, episodeId))
    }

    @PostMapping("/unwatchEpisode/{episodeId}")
    fun unwatchEpisode(@AuthenticationPrincipal principal: CustomPrincipal, @PathVariable episodeId: Long){
        watchService.unwatchEpisode(principal.userId, episodeId)
    }

    @GetMapping("/watched")
    fun getWatchedMovies(@AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<*> {
        return ResponseEntity.ok(watchService.getWatchedTvShows(principal.userId))
    }

    @PostMapping("/unwatch/{showId}")
    fun unwatchShow(@AuthenticationPrincipal principal: CustomPrincipal, @PathVariable showId: Long): ResponseEntity<Unit>{
        watchService.unwatchTvShow(principal.userId, showId)
        return ResponseEntity.ok().build()
    }



    @GetMapping("/mostPopular")
    fun getMostPopular(): ResponseEntity<*> {
        return ResponseEntity.ok(popularityService.getMostPopularTvShows())
    }

    @GetMapping("/comments/{showId}")
    fun getCommentsForShow(@PathVariable showId: Long): ResponseEntity<*> {
        return ResponseEntity.ok(tvshowService.getComments(showId))
    }

    @PostMapping("/comments")
    fun commentShow(
        @AuthenticationPrincipal principal: CustomPrincipal, @RequestBody body: CommentShowDto
    ): ResponseEntity<*> {
        return ResponseEntity.ok(tvshowService.commentShow(principal.userId, body.showId, body.comment))
    }

    @GetMapping("/cast/{showId}")
    fun getCastOfShow(@PathVariable showId: Long): ResponseEntity<*> {
        return ResponseEntity.ok(tvshowService.getCast(showId))
    }

    @GetMapping("/episodes/{showId}/{seasonNumber}")
    fun getEpisodesOfShow(@AuthenticationPrincipal principal: CustomPrincipal, @PathVariable showId: Long, @PathVariable seasonNumber: Long): ResponseEntity<*> {
        return ResponseEntity.ok(tvshowService.getEpisodes(principal.userId,showId, seasonNumber))
    }

}