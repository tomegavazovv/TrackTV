package com.sorsix.backend.exceptions

class FriendshipNotFoundException(userId: Long, friendId: Long): RuntimeException("There is no friendship between the users with id $userId and id $friendId.")