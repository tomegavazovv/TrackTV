package com.sorsix.backend.api

import com.sorsix.backend.authentication.CustomPrincipal
import com.sorsix.backend.exceptions.*
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.friendship.FriendRepository
import com.sorsix.backend.repository.friendship.FriendRequestRepository
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
    val suggestShowService: SuggestShowService,
    val friendRequestRepository: FriendRequestRepository,
    val friendRepository: FriendRepository
) {
    @GetMapping("/friends")
    fun getFriends(@AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            val friends = friendService.getFriends(principal.userId)
            ResponseEntity.ok(friends)
        } catch (ex: FriendNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        }
    }

    @PostMapping("/addFriend/{id}")
    fun addFriend(@PathVariable id: Long, @AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            friendService.addFriend(principal.userId, id).let { ResponseEntity.status(HttpStatus.CREATED).build() }
        } catch (ex: FriendRequestExistsException) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf("error" to ex.message))
        } catch (ex: AlreadyFriendsException) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf("error" to ex.message))
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        }
    }

    @DeleteMapping("/removeFriend/{id}")
    fun removeFriend(@PathVariable id: Long, @AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            friendService.removeFriend(principal.userId, id)
            ResponseEntity.noContent().build()
        } catch (ex: FriendshipNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        }
    }

    @GetMapping("/friendRequests")
    fun friendRequests(@AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            val friendRequests = friendRequestService.findFriendRequestsByReceiverId(principal.userId)
            ResponseEntity.ok(friendRequests)
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        }
    }

    @PostMapping("/acceptRequest/{id}")
    fun acceptRequest(@PathVariable id: Long, @AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            friendRequestService.acceptRequest(id, principal.userId)
            ResponseEntity.noContent().build()
        } catch (ex: FriendRequestNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        }
    }

    @PostMapping("/declineRequest/{id}")
    fun declineRequest(@PathVariable id: Long, @AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<Any> {
        return try {
            friendRequestService.declineRequest(id, principal.userId)
            ResponseEntity.noContent().build()
        } catch (ex: FriendRequestNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        }
    }

    @GetMapping("/searchUsers")
    fun searchUsers(@RequestParam username: String, @AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<Any> {
        val users = userRepository.findAllByNameContainingAndEmailNot(username, principal.name)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/searchUsersClean")
    fun searchUsersClean(@RequestParam username: String, @AuthenticationPrincipal principal: CustomPrincipal): ResponseEntity<Any> {
        val users = friendService.getCleanUsers(username, principal.userId)
        return ResponseEntity.ok(users)
    }

    @PostMapping("/suggestMovie/{movieId}/{friendId}")
    fun suggestMovie(
        @PathVariable movieId: Long, @PathVariable friendId: Long, @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<Any> {
        return try {
            suggestMovieService.suggest(principal.userId, friendId, movieId)
            ResponseEntity.noContent().build()
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        } catch (ex: MovieNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        } catch (ex: MovieSuggestionExists) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf("error" to ex.message))
        }
    }

    @GetMapping("/suggestedMovies")
    fun suggestedMovies(@AuthenticationPrincipal principal: CustomPrincipal) = suggestMovieService.getSuggestions(principal.userId)

    @PostMapping("/suggestShow/{showId}/{friendId}")
    fun suggestShow(
        @PathVariable showId: Long, @PathVariable friendId: Long, @AuthenticationPrincipal principal: CustomPrincipal
    ): ResponseEntity<Any> {
        return try {
            suggestShowService.suggest(principal.userId, friendId, showId)
            ResponseEntity.noContent().build()
        } catch (ex: ShowNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        } catch (ex: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to ex.message))
        } catch (ex: ShowSuggestionExists) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(mapOf("error" to ex.message))
        }
    }

    @GetMapping("/suggestedShows")
    fun suggestedShows(@AuthenticationPrincipal principal: CustomPrincipal) =
        suggestShowService.getSuggestions(principal.userId)
}