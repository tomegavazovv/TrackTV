package com.sorsix.backend.service.implementations

import com.sorsix.backend.domain.User
import com.sorsix.backend.domain.friendship.Friend
import com.sorsix.backend.domain.friendship.FriendRequest
import com.sorsix.backend.repository.UserRepository
import com.sorsix.backend.repository.friendship.FriendRepository
import com.sorsix.backend.repository.friendship.FriendRequestRepository
import com.sorsix.backend.service.FriendService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class FriendServiceImpl(
    val friendRepository: FriendRepository,
    val friendRequestRepository: FriendRequestRepository,
    val userRepository: UserRepository
) : FriendService {
    override fun getFriends(userId: Long): List<User> {
        return userRepository.findFriendsByProfileId(userId)
    }

    override fun addFriend(fromId: Long, toId: Long): FriendRequest {

        val sender = userRepository.findById(fromId).get()
        val reciever = userRepository.findById(toId).get()

        return if(friendRepository.checkFriendship(fromId, toId).isEmpty()) {
            if(friendRequestRepository.findFriendRequestByReceiverAndSender(fromId, toId) == null) {
                friendRequestRepository.save(FriendRequest(senderId = sender, recieverId = reciever))
            } else throw NullPointerException()

        } else throw NullPointerException()

    }

    @Transactional
    override fun removeFriend(userId: Long, friendId: Long) {
        val friendship = friendRepository.checkFriendship(userId, friendId)
        if (friendship.isNotEmpty()) {
            friendRepository.deleteFriend(userId, friendId)
        } else {
//            throw FriendshipNotFoundException("Friendship not found")
        }
    }


}