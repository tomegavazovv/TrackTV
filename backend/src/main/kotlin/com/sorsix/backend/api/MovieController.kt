package com.sorsix.backend.api

import com.sorsix.backend.authentication.CustomPrincipal
import com.sorsix.backend.domain.movie.Movie
import com.sorsix.backend.dto.AddFavoriteCastDto
import com.sorsix.backend.dto.MovieDto
import com.sorsix.backend.dto.RateMovieDto
import com.sorsix.backend.service.*
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/movies")
class MovieController(
    private val favoriteCastService: FavoriteCastService,
    private val ratingService: RatingService,
    private val watchService: WatchService,
    private val popularityService: PopularityService,
    private val movieService: MovieService
) {

    @PostMapping("/favoriteCast")
    fun addFavoriteCastOfMovie(
        @RequestBody body: AddFavoriteCastDto, @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<*> {
            val favoriteMovieCast = favoriteCastService.addFavoriteCastOfMovie(principal.userId, body.id, body.castId)
            return ResponseEntity.ok(favoriteMovieCast)
    }

    @GetMapping("/favoriteCast/{movieId}")
    fun getFavoriteCastOfMovieByUser(@AuthenticationPrincipal principal: CustomPrincipal,
                                     @PathVariable movieId: Long): ResponseEntity<*>{
        return ResponseEntity.ok(favoriteCastService.getFavoriteCastOfMovieByUser(principal.userId, movieId))
    }

    @GetMapping("/topCast/{movieId}")
    fun getTopCast(@PathVariable movieId: Long): ResponseEntity<*>{
        return ResponseEntity.ok(favoriteCastService.getTopFiveCastsOfMovie(movieId))
    }

    @PostMapping("/unwatch/{movieId}")
    fun unwatchMovie(@AuthenticationPrincipal principal: CustomPrincipal, @PathVariable movieId: Long): ResponseEntity<*>{
        watchService.unwatchMovie(principal.userId, movieId)
        return ResponseEntity.ok("Movie with $movieId was successfully marked as unwatched")
    }

    @PostMapping("/rate")
    fun rateMovie(@Validated @RequestBody body: RateMovieDto,
                  @AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<*>{
        return ResponseEntity.ok(ratingService.rateMovie(principal.userId, body.movieId, body.rating, body.comment))
    }

    @GetMapping("/ratingByUser/{movieId}")
    fun getRatingOfMovie(@AuthenticationPrincipal principal: CustomPrincipal, @PathVariable movieId: Long): ResponseEntity<*>{
        return ResponseEntity.ok(ratingService.getMovieRatingByUser(principal.userId, movieId))
    }

    @GetMapping("/ratings/{movieId}")
    fun getRatings(@PathVariable movieId: Long): ResponseEntity<*>{
        return ResponseEntity.ok(ratingService.getMovieRatings(movieId))
    }

    @PostMapping("/addWatched/{movieId}")
    fun addWatchedMovie(@AuthenticationPrincipal principal: CustomPrincipal, @PathVariable movieId: Long): ResponseEntity<*>{
        return ResponseEntity.ok(watchService.addWatchedMovie(principal.userId, movieId))
    }

    @GetMapping("/watched")
    fun getWatchedMovies(@AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<*>{
        return ResponseEntity.ok(watchService.getWatchedMovies(principal.userId))
    }

    @GetMapping("/recentlyWatched")
    fun getRecentlyWatchedMovies(@AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<*>{
        return ResponseEntity.ok(watchService.getRecentlyWatched(principal.userId))
    }

    @GetMapping("/mostPopular")
    fun getMostPopular(): ResponseEntity<*>{
        return ResponseEntity.ok(popularityService.getMostPopularMovies())
    }

    @GetMapping("/search")
    fun searchByTitle(@RequestParam("title") title: String): ResponseEntity<List<Movie>>{
        return ResponseEntity.ok(movieService.searchByTitle(title))
    }

    @GetMapping("/{movieId}")
    fun getMovie(@AuthenticationPrincipal principal: CustomPrincipal, @PathVariable movieId: Long): ResponseEntity<MovieDto>{
        return ResponseEntity.ok(movieService.findById(principal.userId, movieId))
    }

    @GetMapping("/cast/{movieId}")
    fun getCastOfMovie(@PathVariable movieId: Long): ResponseEntity<*> {
        return ResponseEntity.ok(movieService.getCast(movieId))
    }

    // getSuggestedMoviesByFriend - from, to, movieId
}