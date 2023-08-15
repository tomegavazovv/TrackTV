package com.sorsix.backend.service.interfaces

import com.sorsix.backend.domain.friendship.FriendRequest

interface FriendRequestService {
    fun findFriendRequestsByReceiverId(id: Long): List<FriendRequest>
    fun acceptRequest(fromId: Long, toId: Long)
    fun declineRequest(fromId: Long, toId: Long)
}