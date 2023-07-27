package com.sorsix.backend.api

import com.sorsix.backend.authentication.CustomPrincipal
import com.sorsix.backend.exceptions.*
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.service.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FriendsController(
    val friendService: FriendService,
    val friendRequestService: FriendRequestService,
    val userRepository: UserRepository,
    val suggestMovieService: SuggestMovieService,
    val suggestShowService: SuggestShowService
) {
    @GetMapping("/friends")
    fun getFriends(principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            val friends = friendService.getFriends(principal.userId)
            ResponseEntity.ok(friends)
        } catch (ex: FriendNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @PostMapping("/addFriend/{id}")
    fun addFriend(@PathVariable id: Long, principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            friendService.addFriend(principal.userId, id).let { ResponseEntity.status(HttpStatus.CREATED).build() }
        } catch (ex: FriendRequestExistsException) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
        } catch (ex: AlreadyFriendsException) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @DeleteMapping("/removeFriend/{id}")
    fun removeFriend(@PathVariable id: Long, principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            friendService.removeFriend(principal.userId, id)
            ResponseEntity.noContent().build()
        } catch (ex: FriendshipNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @GetMapping("/friendRequests")
    fun friendRequests(principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            val friendRequests = friendRequestService.findFriendRequestsByReceiverId(principal.userId)
            ResponseEntity.ok(friendRequests)
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @PostMapping("/acceptRequest/{id}")
    fun acceptRequest(@PathVariable id: Long, principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            friendRequestService.acceptRequest(id, principal.userId)
            ResponseEntity.noContent().build()
        } catch (ex: FriendRequestNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @PostMapping("/declineRequest/{id}")
    fun declineRequest(@PathVariable id: Long, principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            friendRequestService.declineRequest(id, principal.userId)
            ResponseEntity.noContent().build()
        } catch (ex: FriendRequestNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @GetMapping("/searchUsers")
    fun searchUsers(@RequestParam username: String): ResponseEntity<Any> {
        val users = userRepository.findByName(username)
        return ResponseEntity.ok(users)
    }

    @PostMapping("/suggestMovie/{movieId}/{friendId}")
    fun suggestMovie(
        @PathVariable movieId: Long, @PathVariable friendId: Long, principal: CustomPrincipal
    ): ResponseEntity<Any> {
        return try {
            suggestMovieService.suggest(principal.userId, friendId, movieId)
            ResponseEntity.noContent().build()
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        } catch (ex: MovieNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        } catch (ex: MovieSuggestionExists) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
        }
    }

    @GetMapping("/suggestedMovies")
    fun suggestedMovies(principal: CustomPrincipal) = suggestMovieService.getSuggestions(principal.userId)

    @PostMapping("/suggestShow/{showId}/{friendId}")
    fun suggestShow(
        @PathVariable showId: Long, @PathVariable friendId: Long, principal: CustomPrincipal
    ): ResponseEntity<Any> {
        return try {
            suggestShowService.suggest(principal.userId, friendId, showId)
            ResponseEntity.noContent().build()
        } catch (ex: ShowNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
        }
    }

    @GetMapping("/suggestedShows")
    fun suggestedShows(@AuthenticationPrincipal principal: CustomPrincipal) =
        suggestShowService.getSuggestions(principal.userId)
}









// {
//      "error": "No friends found."
// }