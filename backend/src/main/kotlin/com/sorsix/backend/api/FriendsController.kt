package com.sorsix.backend.api

import com.sorsix.backend.authentication.CustomPrincipal
import com.sorsix.backend.service.FriendRequestService
import com.sorsix.backend.service.FriendService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FriendsController(
    val friendService: FriendService,
    val friendRequestService: FriendRequestService
) {
    @GetMapping("/friends")
    fun getFriends(principal: CustomPrincipal) = friendService.getFriends(principal.userId)

    @PostMapping("/addFriend")
    fun addFriend(@RequestParam id: Long, principal: CustomPrincipal): ResponseEntity<Void> =
        friendService.addFriend(principal.userId, id).let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @DeleteMapping("/removeFriend")
    fun removeFriend(@RequestParam id: Long, principal: CustomPrincipal) = friendService.removeFriend(principal.userId, id)

    @GetMapping("/friendRequests")
    fun friendRequests( principal: CustomPrincipal) = friendRequestService.findFriendRequestsByRecieverId(principal.userId)

    @PostMapping("/acceptRequest")
    fun acceptRequest(@RequestParam id: Long, principal: CustomPrincipal) = friendRequestService.acceptRequest(id, principal.userId)

    @PostMapping("/declineRequest")
    fun declineRequest(@RequestParam id: Long, principal: CustomPrincipal) = friendRequestService.declineRequest(id, principal.userId)
}
