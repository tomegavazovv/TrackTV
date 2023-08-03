package com.sorsix.backend.api

import com.sorsix.backend.authentication.CustomPrincipal
import com.sorsix.backend.dto.AddFavoriteCastDto
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
    private val tvshowService: TvShowService
) {

    @GetMapping("/{showId}")
    fun getShow(@PathVariable showId: Long): ResponseEntity<*>{
        return ResponseEntity.ok(tvshowService.getById(showId))
    }

    @PostMapping("/favoriteCast")
    fun addFavoriteCastOfTvShow(
        @RequestBody body: AddFavoriteCastDto, @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<*> {
        return ResponseEntity.ok(
            favoriteCastService.addFavoriteCastOfTvShow(
                principal.userId, body.id, body.castId
            )
        )
    }

    @GetMapping("/favoriteCast/{tvshowId}")
    fun getFavoriteCastOfTvShowByUser(
        @AuthenticationPrincipal principal: CustomPrincipal, @PathVariable tvshowId: Long
    ): ResponseEntity<*> {
        return ResponseEntity.ok(favoriteCastService.getFavoriteCastOfTvShowByUser(principal.userId, tvshowId))
    }

    @GetMapping("/topCast/{tvshowId}")
    fun getTopCast(@PathVariable tvshowId: Long): ResponseEntity<*> {
        return ResponseEntity.ok(favoriteCastService.getTopFiveCastsOfMovie(tvshowId))
    }

    @PostMapping("/rate")
    fun rateTvShow(
        @Validated @RequestBody body: RateEpisodeDto, @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<*> {
        return ResponseEntity.ok(
            ratingService.rateEpisodeOfTvShow(
                principal.userId,
                body.episodeId,
                body.rating,
                body.comment
            )
        )
    }

    @GetMapping("/ratingByUser/{episodeId}")
    fun getRatingOfEpisode(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable episodeId: Long
    ): ResponseEntity<*> {
        return ResponseEntity.ok(ratingService.getRatingOfTvShowEpisodeByUser(principal.userId, episodeId))
    }

    @GetMapping("/ratings/{episodeId}")
    fun getRatingsOfTvShowEpisode(@PathVariable episodeId: Long): ResponseEntity<*> {
        return ResponseEntity.ok(ratingService.getRatingOfTvShowEpisode(episodeId))
    }

    @PostMapping("/watched/{episodeId}")
    fun addWatchedEpisode(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable episodeId: Long
    ): ResponseEntity<*> {
        return ResponseEntity.ok(watchService.addWatchedEpisode(principal.userId, episodeId))
    }

    @GetMapping("/watched")
    fun getWatchedMovies(@AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<*> {
        return ResponseEntity.ok(watchService.getWatchedTvShows(principal.userId))
    }

    @GetMapping("/mostPopular")
    fun getMostPopular(): ResponseEntity<*> {
        return ResponseEntity.ok(popularityService.getMostPopularTvShows())
    }

}