package com.sorsix.backend.api

import com.sorsix.backend.domain.friendship.FriendRequest
import com.sorsix.backend.repository.friendship.FriendRequestRepository
import com.sorsix.backend.repository.user.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


// privremeno za testiranje
@RestController
@RequestMapping("/api/")
class Temp(val repo:FriendRequestRepository) {
    @GetMapping("/findAll")
    fun getFriendRequests(): List<FriendRequest> = repo.findAll()
}