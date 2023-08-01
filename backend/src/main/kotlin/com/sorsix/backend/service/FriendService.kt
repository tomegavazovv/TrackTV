package com.sorsix.backend.service

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.friendship.FriendRequest

interface FriendService {

    fun getFriends(userId: Long) : List<User>

    fun addFriend(fromId: Long, toId: Long) : FriendRequest

    fun removeFriend(userId: Long, friendId: Long)
}