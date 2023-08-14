package com.sorsix.backend.api

import com.sorsix.backend.authentication.CustomPrincipal
import com.sorsix.backend.domain.Cast
import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.domain.user.WatchMovie
import com.sorsix.backend.domain.views.TopFiveCastOfMovieViewEntity
import com.sorsix.backend.dto.*
import com.sorsix.backend.service.*
import com.sorsix.backend.service.interfaces.CastService.CastService
import com.sorsix.backend.service.interfaces.CommentService.CommentService
import com.sorsix.backend.service.interfaces.MovieService
import com.sorsix.backend.service.interfaces.RatingService.RatingService
import com.sorsix.backend.service.interfaces.WatchService.WatchService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/movies")
class MovieController(
    private val castService: CastService,
    private val ratingService: RatingService,
    private val commentService: CommentService,
    private val watchService: WatchService,
    private val movieService: MovieService
) {

    @PostMapping("/favoriteCast")
    fun addFavoriteCastOfMovie(
        @RequestBody body: AddFavoriteCastDto, @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<Cast> {
        val favoriteMovieCast = castService.addFavoriteCastOfMovie(principal.userId, body.id, body.castId)
        return ResponseEntity.ok(favoriteMovieCast)
    }

    @GetMapping("/favoriteCast/{movieId}")
    fun getFavoriteCastOfMovieByUser(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable movieId: Long
    ): ResponseEntity<Cast> {
        return ResponseEntity.ok(castService.getFavoriteCastOfMovieByUser(principal.userId, movieId))
    }

    @PostMapping("/rate")
    fun rateMovie(@AuthenticationPrincipal principal: CustomPrincipal, @RequestBody body: RateMovieDto): ResponseEntity<Unit>{
        ratingService.rateMovie(principal.userId, body.movieId, body.rating.toInt())
        return ResponseEntity.ok().build()
    }

    @GetMapping("/topCast/{movieId}")
    fun getTopCast(@PathVariable movieId: Long): ResponseEntity<List<TopFiveCastOfMovieViewEntity>> {
        return ResponseEntity.ok(castService.getTopFiveCastsOfMovie(movieId))
    }

    @PostMapping("/unwatch/{movieId}")
    fun unwatchMovie(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable movieId: Long
    ): ResponseEntity<Void> {
        watchService.unwatchMovie(principal.userId, movieId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/comment")
    fun rateMovie(
        @Validated @RequestBody body: com.sorsix.backend.dto.CommentMovieBody,
        @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<List<MovieCommentDto>> {
        return ResponseEntity.ok(commentService.commentMovie(principal.userId, body.movieId, body.comment))
    }

    @GetMapping("/averageRating/{movieId}")
    fun getRatings(@PathVariable movieId: Long): ResponseEntity<Double> {
        return ResponseEntity.ok(ratingService.getAverageMovieRating(movieId))
    }

    @GetMapping("/comments/{movieId}")
    fun getComments(@PathVariable movieId: Long): ResponseEntity<List<MovieCommentDto>> {
        return ResponseEntity.ok(commentService.getMovieComments(movieId))
    }

    @PostMapping("/addWatched/{movieId}")
    fun addWatchedMovie(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable movieId: Long
    ): ResponseEntity<WatchMovie> {
        return ResponseEntity.ok(watchService.addWatchedMovie(principal.userId, movieId))
    }

    @GetMapping("/watched")
    fun getWatchedMovies(@AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<List<WatchMovie>> {
        return ResponseEntity.ok(watchService.getWatchedMovies(principal.userId))
    }

    @GetMapping("/recentlyWatched")
    fun getRecentlyWatchedMovies(@AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<List<WatchMovie>> {
        return ResponseEntity.ok(watchService.getRecentlyWatched(principal.userId))
    }

    @GetMapping("/mostPopular")
    fun getMostPopular(): ResponseEntity<List<Movie>> {
        return ResponseEntity.ok(movieService.getMostPopularMovies())
    }

    @GetMapping("/search")
    fun searchByTitle(@RequestParam("title") title: String): ResponseEntity<List<Movie>> {
        return ResponseEntity.ok(movieService.searchByTitle(title))
    }

    @GetMapping("/{movieId}")
    fun getMovie(
        @AuthenticationPrincipal principal: CustomPrincipal,
        @PathVariable movieId: Long
    ): ResponseEntity<MovieDto> {
        return ResponseEntity.ok(movieService.findById(principal.userId, movieId))
    }

    @GetMapping("/cast/{movieId}")
    fun getCastOfMovie(@PathVariable movieId: Long): ResponseEntity<List<Cast>> {
        return ResponseEntity.ok(castService.getMovieCast(movieId))
    }

}