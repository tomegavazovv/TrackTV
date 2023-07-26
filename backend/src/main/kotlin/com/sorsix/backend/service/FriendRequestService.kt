package com.sorsix.backend.service

import com.sorsix.backend.domain.friendship.FriendRequest

interface FriendRequestService {

    fun findFriendRequestsByRecieverId(id: Long): List<FriendRequest>

    fun acceptRequest(fromId: Long, toId: Long)
    fun declineRequest(fromId: Long, toId: Long)
}