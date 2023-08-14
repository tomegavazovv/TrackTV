package com.sorsix.backend.exceptions

class FriendRequestExistsException(fromId: Long, toId: Long): RuntimeException("There is an existing friend request between both users with id $fromId and id $toId.")