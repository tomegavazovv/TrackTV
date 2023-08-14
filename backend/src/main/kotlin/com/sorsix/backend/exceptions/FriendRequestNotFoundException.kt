package com.sorsix.backend.exceptions

class FriendRequestNotFoundException(fromId: Long, toId: Long): RuntimeException("There is no friend request between users with id $fromId and $toId.")